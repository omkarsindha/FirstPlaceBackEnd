package com.example.firstplacebackend.Controllers;

import com.example.firstplacebackend.Bean.User;
import com.example.firstplacebackend.Database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    DatabaseAccess da;

    @GetMapping("/")
    public String getHome() {
        da.findUserForLogin(new User());
        return "hi.html";
    }

}
