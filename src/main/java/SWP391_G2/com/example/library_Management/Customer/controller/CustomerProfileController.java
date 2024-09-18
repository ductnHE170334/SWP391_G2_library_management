package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class CustomerProfileController {
    @Autowired
    private CustomerProfileService customerProfileService;
}
