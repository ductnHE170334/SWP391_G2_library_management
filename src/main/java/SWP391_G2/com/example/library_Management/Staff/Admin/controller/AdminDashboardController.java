package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.Borrow_history;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("totalPendingRequests", adminDashboardService.getTotalPendingRequests());
        model.addAttribute("totalRejectedRequests", adminDashboardService.getTotalRejectedRequests());
        model.addAttribute("totalApprovedRequests", adminDashboardService.getTotalApprovedRequests());
        model.addAttribute("totalCustomers", adminDashboardService.getTotalCustomers());
        model.addAttribute("totalBooks", adminDashboardService.getTotalBooks());

        // Lấy dữ liệu mượn sách theo tháng
        List<Integer> totalBorrowCounts = adminDashboardService.getTotalBorrowCounts();
        List<Integer> numberOfBooksBorrowed = adminDashboardService.getNumberOfBooksBorrowed();

        model.addAttribute("monthlyBorrowCounts", totalBorrowCounts);
        model.addAttribute("monthlyBooksBorrowed", numberOfBooksBorrowed);

        return "Staff/dashboard/Views/admin_dashboard";
    }


    @GetMapping("/borrow-detail")
    public String showBorrowRequests(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "bookName", required = false) String bookName,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            Model model) {

        // Lấy danh sách yêu cầu mượn đang chờ với phân trang và các bộ lọc
        Page<Borrow_index> RequestsPage = adminDashboardService.getAllBorrowIndexes(
                PageRequest.of(page - 1, size), status, bookName, startDate, endDate);

        // Thêm danh sách yêu cầu mượn và thông tin phân trang vào model
        model.addAttribute("BorrowIndexes", RequestsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", RequestsPage.getTotalPages());
        model.addAttribute("totalItems", RequestsPage.getTotalElements());

        // Thêm các tham số bộ lọc vào model để duy trì trạng thái
        model.addAttribute("status", status);
        model.addAttribute("bookName", bookName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "Staff/dashboard/Views/borrow_detail";
    }

}

