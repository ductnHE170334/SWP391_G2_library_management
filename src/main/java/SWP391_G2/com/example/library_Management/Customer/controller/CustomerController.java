
package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.dto.request.CustomerLoginRequest;
import SWP391_G2.com.example.library_Management.Customer.service.CustomerService;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer/register";
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer) {
        customerService.registerCustomer(customer);
        return "redirect:/customer/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginUser", new CustomerLoginRequest());
        return "Customer/login";
    }

    @PostMapping("/login")
    public String loginCustomer(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isAuthenticated = customerService.login(email, password);
        if (isAuthenticated) {
            return "redirect:/customer/welcome";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "Customer/login";
        }
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model, Principal principal) {
        model.addAttribute("name", principal.getName());
        return "Customer/welcome";
    }
}
