package com.example.demo.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api")
public class MainRestController {
    @GetMapping("/{accountId}/{imageLink}")
    public byte[] getImage(
            @PathVariable("accountId") long id,
            @PathVariable("imageLink") String imageLink
    ) {
        File file = new File("src/main/resources/static/image_base/user_"+id+"/"+ imageLink);
        byte[] byteFile = new byte[0];
        try {
            byteFile = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteFile;
    }

}