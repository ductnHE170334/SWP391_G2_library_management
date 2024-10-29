package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "Staff/fragments/admin_dashboard";  // Trả về tên view template
    }
}
