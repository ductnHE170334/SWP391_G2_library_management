package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerApplicationService;
import SWP391_G2.com.example.library_Management.Entity.Application;
import SWP391_G2.com.example.library_Management.Entity.Application_Type;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/application")
public class CustomerApplicationController {
    @Autowired
    private CustomerApplicationService customerApplicationService;
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
            model.addAttribute("message", "User not logged in.");
            return "redirect:/login";
        }

        // Lấy danh sách Application theo userId
        List<Application> applications = customerApplicationService.getApplicationsByUserId(userId);
        model.addAttribute("applications", applications);

        return "Customer/Application/ListApplication";
    }
    @GetMapping("/add")
    public String addApplicationView(HttpSession session, Model model) {
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;

        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

        if (userId == null) {
            model.addAttribute("message", "User not logged in.");
            return "redirect:/login";
        }

        List<Application_Type> applicationTypes = customerApplicationService.getAllApplicationTypes();
        model.addAttribute("applicationTypes", applicationTypes);
        model.addAttribute("userId", userId);

        return "Customer/Application/AddApplication";
    }
}

