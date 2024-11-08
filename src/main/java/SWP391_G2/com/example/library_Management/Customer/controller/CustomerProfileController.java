package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerProfileService;
import SWP391_G2.com.example.library_Management.Entity.User;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping
    public String getUserProfile(HttpSession session, Model model) {
        User user = customerProfileService.getUser(session);
        if (user == null) {
            model.addAttribute("loginMessage", "Please login");
            return "redirect:/login"; // Redirect to an error page if needed
        }
        model.addAttribute("user", user);
        return "Customer/Profile/profileUser";
    }

    // Show the update form for user
    @GetMapping("/update")
    public String showUpdateForm(HttpSession session, Model model) {
        User user = customerProfileService.getUser(session);
        if (user == null) {
            model.addAttribute("error", "User not found in session.");
            return "error"; // Redirect to an error page if needed
        }
        model.addAttribute("user", user);
        return "Customer/Profile/updateProfile";
    }

    // Update user information
    @PostMapping("/update")
    public String updateUser(HttpSession session,
                             @ModelAttribute User user,
                             @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                             RedirectAttributes redirectAttributes) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "User ID not found in session.");
            return "redirect:/login";
        }

        // Validate email format
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!user.getEmail().matches(emailRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid email format!");
            return "redirect:/profile/update"; // Redirect back to the update form
        }

        // Validate phone format (assuming phone should be numeric and 10-15 digits)
        String phoneRegex = "^\\d{10,15}$";
        if (!user.getPhone().matches(phoneRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid phone number format!");
            return "redirect:/profile/update"; // Redirect back to the update form
        }

        // Update user in the database
        customerProfileService.updateUser(userId, user);

        // Add success message
        redirectAttributes.addFlashAttribute("message", "Your profile has been updated successfully!");
        return "redirect:/profile"; // Redirect to the profile view
    }
}
