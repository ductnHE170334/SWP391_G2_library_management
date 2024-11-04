package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerProfileService;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService customerProfileService;


//    // Get a user by ID
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") int id, Model model) {
//        User user = customerProfileService.getUser(String.valueOf(id));
//        model.addAttribute("user", user);
//        return "Customer/Profile/profileUser";
//    }

    @GetMapping("/5")
    public String getUserById(@PathVariable(value = "id", required = false) String id, Model model) {
        User user = customerProfileService.getUser("5");
        model.addAttribute("user", user);
        return "Customer/Profile/profileUser";
    }

    // Show the update form for user
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = customerProfileService.getUser(String.valueOf(id));
        model.addAttribute("user", user);
        return "Customer/Profile/profileUser/updateProfile";
    }

    // Update user information
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id,
                             @ModelAttribute User user,
                             @RequestParam("roleId") int roleId,
                             @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                             RedirectAttributes redirectAttributes) {

        // Validate email format
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!user.getEmail().matches(emailRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid email format!");
            return "redirect:/profile/update/" + id; // Redirect back to the update form
        }

        // Validate phone format (assuming phone should be numeric and 10-15 digits)
        String phoneRegex = "^\\d{10,15}$";
        if (!user.getPhone().matches(phoneRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid phone number format!");
            return "redirect:/profile/update/" + id; // Redirect back to the update form
        }

        // Update user in the database
        customerProfileService.updateUser(id, user);

        // Add success message
        redirectAttributes.addFlashAttribute("message", "User with ID " + id + " has been updated successfully!");
        return "redirect:/profile"; // Redirect to the profile list
    }
}
