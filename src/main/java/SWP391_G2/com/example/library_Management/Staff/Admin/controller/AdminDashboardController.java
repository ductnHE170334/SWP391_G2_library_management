package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/dashboard")
public class AdminDashboardController {
    private final AdminDashboardService adminDashboardService;

    @Autowired
    public AdminDashboardController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/list")
    public String showDashboardStats(Model model) {
        // Thêm từng giá trị thống kê vào model
        model.addAttribute("totalPendingRequests", adminDashboardService.getTotalPendingRequests());
        model.addAttribute("totalRejectedRequests", adminDashboardService.getTotalRejectedRequests());
        model.addAttribute("totalApprovedRequests", adminDashboardService.getTotalApprovedRequests());
        model.addAttribute("totalCustomers", adminDashboardService.getTotalCustomers());
        model.addAttribute("totalBooks", adminDashboardService.getTotalBooks());
<<<<<<< HEAD

        return "Staff/dashboard/Views/admin_dashboard";
    }
    @GetMapping("/pending")
    public String showPendingBorrowRequests(Model model) {
        List<Borrow_index> pendingRequests = adminDashboardService.getPendingBorrowIndexes();
        model.addAttribute("pendingBorrowIndexes", pendingRequests);

        return "Staff/dashboard/Views/pending_detail"; // Đường dẫn đến view của bạn
    }

=======

        return "Staff/fragments/admin_dashboard";  // Trả về tên view template
    }
>>>>>>> 508f15ffe308a64dbb03ad25e72150da23dc9043
}
