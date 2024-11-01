package SWP391_G2.com.example.library_Management.User.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class UserLogoutController {

    @GetMapping
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home/list"; // or "redirect:/login" if you prefer
    }
}
