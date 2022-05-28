package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Computer;
import com.example.demo.model.Order;
import com.example.demo.service.MainService;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/computer-shop")
@AllArgsConstructor
public class MainController {

    private final MainService mainService;
    private final OrderService orderService;

    private List<Computer> purchaseList = new ArrayList<>();

    @GetMapping
    public String getPage(Model model, Authentication authentication) {
        model.addAttribute("computer_list", mainService.getComputerList());
        model.addAttribute("purchaseList", purchaseList);

        if(authentication != null){
            if(authentication.getName().equalsIgnoreCase("admin")){
                model.addAttribute("isAdmin", true);
            }
        }



        return "shop";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }


    @PostMapping
    public String saveAccount(
            @Valid @ModelAttribute("account") Account theAccount,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        mainService.saveAccount(theAccount);
        return "redirect:/login";
    }

    @GetMapping("/info/{id}")
    public String getInfoPage(
            @PathVariable("id") long id,
            Model model
    ) {
        Computer theComputer = mainService.getComputerById(id);
        model.addAttribute("computer_info", theComputer);
        return "info-page";
    }

    @GetMapping("/basket/buy/{id}")
    public String buyProcess(
            @PathVariable("id") long id

    ) {
        Computer computer = mainService.getComputerById(id);
        if (!purchaseList.contains(computer)) {
            purchaseList.add(computer);
        }
        return "redirect:/computer-shop";
    }

    @GetMapping("/basket/delete/{id}")
    public String deleteProcess(
            @PathVariable("id") long id
    ) {
        purchaseList.removeIf(computer -> computer.getId() == id);

        return "redirect:/computer-shop";
    }

    @GetMapping("/purchase")
    public String getPurchase(Model model) {
        model.addAttribute("order", new Order());
        return "order-page";
    }

    @PostMapping("/finishPurchase")
    public String finishPurchase(
            @Valid @ModelAttribute("order") Order order,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            return "order-page";
        }
        order.setOrderedComputerList(purchaseList);
        orderService.saveOrder(order);
        purchaseList.clear();
        System.out.println(order);
        return "redirect:/computer-shop";
    }

    @GetMapping("/myorders")
    public String getmyOrdersPage(Model model) {
        model.addAttribute("orderList", orderService.getAllOrders());
        return "myorder-page";
    }


}