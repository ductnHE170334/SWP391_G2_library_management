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
            session.setAttribute("userId", user.getId());
            session.setAttribute("userLastName", user.getLastName());
            return "redirect:/home/list";
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "Customer/LoginRegister/signIn";
        }
    }
}

