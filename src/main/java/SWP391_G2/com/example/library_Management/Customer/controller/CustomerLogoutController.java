package SWP391_G2.com.example.library_Management.Customer.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class CustomerLogoutController {
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Huỷ session khi người dùng đăng xuất
        return "redirect:/Customer/login_register"; // Chuyển hướng đến trang đăng nhập sau khi đăng xuất
    }
}
