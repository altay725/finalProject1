package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Computer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ComputerRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MainService {

    private final AccountRepository accountRepository;
    private final ComputerRepository computerRepository;

    @Transactional
    public Account getAccountByUsername(String username){
        return accountRepository.getAccountByUsername(username);
    }

    @Transactional
    public void addComputer(Computer theComputer, MultipartFile multipartFile) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Account owner_account = getAccountByUsername(authentication.getName());
        theComputer.setTheAccount(owner_account);


        String imageName = multipartFile.getOriginalFilename() + UUID.randomUUID();

        File file = new File("src/main/resources/static/image_base/user_"+owner_account.getId());
        if(!file.exists()){
            file.mkdir();
        }

        Path path = Paths.get
                (file.getPath(),"/" + imageName);

        try {
            multipartFile.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        theComputer.setImageLink(imageName);
        computerRepository.save(theComputer);
    }
    @Transactional
    public void saveAccount(Account theAccount) {
        accountRepository.save(theAccount);
    }
    @Transactional
    public List<Computer> getComputerList() {
        return computerRepository.findAll();
    }
    @Transactional
    public List<Computer> getMyComputerList(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Account owner_account = getAccountByUsername(authentication.getName());
        System.out.println(authentication);

        List<Computer> computerList = new ArrayList<>();
        for(Computer computer : computerRepository.findAll()){
            if(computer.getTheAccount().getId() ==owner_account.getId()){
                computerList.add(computer);
            }
        }
        return computerList;
    }

    public Computer getComputerById(long id) {
        return computerRepository.getComputerById(id);
    }

    public void deleteComputerById(long id) {
        computerRepository.deleteById(id);
    }


}