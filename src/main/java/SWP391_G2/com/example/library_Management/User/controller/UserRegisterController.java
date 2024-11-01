package SWP391_G2.com.example.library_Management.User.controller;

import SWP391_G2.com.example.library_Management.User.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/register")
public class UserRegisterController {
    @Autowired
    private UserRegisterService userRegisterService;
}
