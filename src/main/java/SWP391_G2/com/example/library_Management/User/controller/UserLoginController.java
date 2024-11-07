package SWP391_G2.com.example.library_Management.User.controller;

import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.User.service.UserLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    // Display login page
    @GetMapping
    public String showLoginPage() {
        return "Customer/LoginRegister/signIn";
    }

    @PostMapping("/process")
    public String processLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        User user = userLoginService.loginUser(email, password);

        if (user != null) {
            // Store user information in the session
            session.setAttribute("userId", user.getId());
            session.setAttribute("userLastName", user.getLastName());

            // Check the user's role and redirect accordingly
            int roleId = user.getRole().getId();
            switch (roleId) {
                case 1:
                    return "redirect:/home/list";
                case 2:
                    return "redirect:/news/list";
                case 3:
                    return "redirect:/librarian/trackBorrowBook";
                case 4:
                    return "redirect:/books";
                default:
                    model.addAttribute("error", "Unknown role.");
                    return "Customer/LoginRegister/signIn";
            }
        } else {
            // If login fails, display an error message
            model.addAttribute("error", "Invalid email or password.");
            return "Customer/LoginRegister/signIn";
        }
    }

}

