package SWP391_G2.com.example.library_Management.Customer.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/application")
public class CustomerApplicationController {
    @GetMapping
    public String getListApplication(HttpSession session, Model model) {
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;


        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

        if (userId == null) {
            // Xử lý trường hợp userId không được thiết lập trong session
            model.addAttribute("message", "User not logged in.");
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập hoặc trang phù hợp
        }
        model.addAttribute("userId", userId);

        return "Customer/Application/ListApplication";
    }
}

