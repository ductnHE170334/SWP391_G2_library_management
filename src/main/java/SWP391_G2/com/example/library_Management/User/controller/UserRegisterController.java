package SWP391_G2.com.example.library_Management.User.controller;

import SWP391_G2.com.example.library_Management.Entity.Role;
import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.User.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;



    // Display registration page
    @GetMapping
    public String showRegistrationPage() {
        return "Customer/LoginRegister/register"; // Adjust the path as needed
    }

    // Handle registration request
    @PostMapping("/process")
    public String processRegistration(User user, Model model) {
        // Retrieve the default role from the database (assumed role ID is 1)
        Role defaultRole = userRegisterService.findRoleById(1); // Get role with ID 1

        if (defaultRole != null) {
            user.setRole(defaultRole); // Set the user role to the retrieved role
        } else {
            model.addAttribute("error", "Default role not found.");
            return "Customer/LoginRegister/register"; // Show the registration page again
        }

        // Call service to register the user
        boolean isRegistered = userRegisterService.registerUser(user);

        if (isRegistered) {
            // Registration successful, redirect to login or home page
            return "redirect:/login"; // Redirect to login page
        } else {
            // Registration failed, show error message
            model.addAttribute("error", "Registration failed. Please try again.");
            return "Customer/LoginRegister/register"; // Show the registration page again
        }
    }
}
