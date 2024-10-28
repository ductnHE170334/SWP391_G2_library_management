package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AdminDashboardController {
    private final AdminDashboardService adminDashboardService;

    @Autowired
    public AdminDashboardController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("Staff/fragments/dashboard")
    public String showDashboardStats(Model model) {
        model.addAttribute("dashboardStats", new DashboardStats(
                adminDashboardService.getTotalPendingRequests(),
                adminDashboardService.getTotalRejectedRequests(),
                adminDashboardService.getTotalApprovedRequests(),
                adminDashboardService.getTotalCustomers(),
                adminDashboardService.getTotalBooks()
        ));
        return "Staff/dashboard";
    }

    // Inner class to hold the response data
    public static class DashboardStats {
        private long pendingRequests;
        private long rejectedRequests;
        private long approvedRequests;
        private long totalCustomers;
        private long totalBooks;

        public DashboardStats(long pendingRequests, long rejectedRequests, long approvedRequests, long totalCustomers, long totalBooks) {
            this.pendingRequests = pendingRequests;
            this.rejectedRequests = rejectedRequests;
            this.approvedRequests = approvedRequests;
            this.totalCustomers = totalCustomers;
            this.totalBooks = totalBooks;
        }

        // Getters
        public long getPendingRequests() {
            return pendingRequests;
        }

        public long getRejectedRequests() {
            return rejectedRequests;
        }

        public long getApprovedRequests() {
            return approvedRequests;
        }

        public long getTotalCustomers() {
            return totalCustomers;
        }

        public long getTotalBooks() {
            return totalBooks;
        }
    }
}
