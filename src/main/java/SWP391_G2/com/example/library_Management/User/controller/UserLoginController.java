package SWP391_G2.com.example.library_Management.User.controller;

import SWP391_G2.com.example.library_Management.User.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/login")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
}
