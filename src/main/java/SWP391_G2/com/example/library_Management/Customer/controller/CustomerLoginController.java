package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/login")
public class CustomerLoginController {
    @Autowired
    private CustomerLoginService customerLoginService;
}
