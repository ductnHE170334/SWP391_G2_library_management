package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerApplicationService;
import SWP391_G2.com.example.library_Management.Customer.service.CustomerApplicationTypeService;
import SWP391_G2.com.example.library_Management.Customer.service.CustomerBorrowIndexService;
import SWP391_G2.com.example.library_Management.Customer.service.CustomerUserService;
import SWP391_G2.com.example.library_Management.Entity.Application;
import SWP391_G2.com.example.library_Management.Entity.Application_Type;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/application")
public class CustomerApplicationController {
    @Autowired
    CustomerUserService customerUserService;
    @Autowired
    CustomerApplicationTypeService customerApplicationTypeService;
    @Autowired
    private CustomerBorrowIndexService customerBorrowIndexService;
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
        for (Application app : applications) {
            System.out.println("Application ID: " + app.getId());
            System.out.println("Reason: " + app.getReason());
            System.out.println("Status: " + app.getStatus());
            if (app.getBorrowIndex() != null) {
                System.out.println("Borrow Book: " + app.getBorrowIndex().getBook_item().getBook());
            } else {
                System.out.println("Borrow Index: Not associated");
            }
        }
        model.addAttribute("applications", applications);

        return "Customer/Application/ListApplication";
    }
    @GetMapping("/add")
    public String addApplicationView(
            HttpSession session,
            Model model,
            @RequestParam("borrowId") Long borrowId
    ) {
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
        Borrow_index borrowIndex = customerBorrowIndexService.getBorrowIndexById(borrowId);

        List<Application_Type> applicationTypes = customerApplicationService.getAllApplicationTypes();
        model.addAttribute("applicationTypes", applicationTypes);
        model.addAttribute("userId", userId);
        model.addAttribute("borrowIndex", borrowIndex);

        return "Customer/Application/AddApplication";
    }
    @PostMapping("/submitApplication")
    public String submitApplication(
            @RequestParam("borrowId") Long borrowId,
            @RequestParam("applicationTypeId") int applicationTypeId,
            @RequestParam("reason") String reason,
            HttpSession session,
            Model model) {
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

        // Find the Borrow Index
        Borrow_index borrowIndex = customerBorrowIndexService.getBorrowIndexById(borrowId);
        if (borrowIndex == null) {
            model.addAttribute("message", "Borrow Index not found.");
            return "redirect:/error";
        }

        // Find the Application Type
        Application_Type applicationType = customerApplicationTypeService.getApplicationTypeById(applicationTypeId);
        if (applicationType == null) {
            model.addAttribute("message", "Application Type not found.");
            return "redirect:/error";
        }

        // Find the User
        User user = customerUserService.findById(userId);
        if (user == null) {
            model.addAttribute("message", "User not found.");
            return "redirect:/error";
        }

        // Create a new Application
        Application application = new Application();
        application.setReason(reason);
        application.setStatus("Waiting"); // Default status
        application.setApplication_type(applicationType);
        application.setUser(user);
        application.setBorrowIndex(borrowIndex);

        // Save the Application
        customerApplicationService.saveApplication(application);


        return "redirect:/application";
    }
}

