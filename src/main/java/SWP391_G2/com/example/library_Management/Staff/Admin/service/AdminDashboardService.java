package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.Status;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDashboardService {
    private final AdminDashboardRepository adminDashboardRepository;

    @Autowired
    public AdminDashboardService(AdminDashboardRepository adminDashboardRepository) {
        this.adminDashboardRepository = adminDashboardRepository;
    }

    public long getTotalPendingRequests() {
        return adminDashboardRepository.getTotalPendingRequests();
    }

    public long getTotalRejectedRequests() {
        return adminDashboardRepository.getTotalRejectedRequests();
    }

    public long getTotalApprovedRequests() {
        return adminDashboardRepository.getTotalApprovedRequests();
    }

    public long getTotalCustomers() {
        return adminDashboardRepository.getTotalCustomers();
    }

    public long getTotalBooks() {
        return adminDashboardRepository.getTotalBooks();
    }
    public List<Borrow_index> getPendingBorrowIndexes() {
        return adminDashboardRepository.getRejectedBorrowIndexes();
    }
    public List<Borrow_index> getRejectedBorrowIndexes() {
        return adminDashboardRepository.getRejectedBorrowIndexes();
    }
    public List<Borrow_index> getApprovedBorrowIndexes() {
        return adminDashboardRepository.getApprovedBorrowIndexes();
    }
    public Page<Borrow_index> getAllBorrowIndexes(Pageable pageable, String status, String bookName, String startDateStr, String endDateStr) {
        LocalDate startDate = null; // Khai báo biến cho ngày bắt đầu
        LocalDate endDate = null; // Khai báo biến cho ngày kết thúc

        // Phân tích chuỗi ngày thành LocalDate
        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDate = LocalDate.parse(startDateStr); // Phân tích đúng
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDate = LocalDate.parse(endDateStr); // Phân tích đúng
        }

        // Chuyển đổi LocalDate thành LocalDateTime
        LocalDateTime borrowDateTime = (startDate != null) ? startDate.atStartOfDay() : null; // Bắt đầu của ngày
        LocalDateTime returnDateTime = (endDate != null) ? endDate.atTime(LocalTime.MAX) : null; // Kết thúc của ngày

        // Thêm logic lọc nếu cần
        // Có thể thực hiện lọc theo trạng thái
        if (status != null && !status.isEmpty()) {
            // Áp dụng logic lọc trạng thái ở đây nếu cần
        }

        // Có thể thực hiện lọc theo tên sách
        if (bookName != null && !bookName.isEmpty()) {
            // Áp dụng logic lọc theo tên sách ở đây nếu cần
        }

        // Gọi kho chứa của bạn để lấy dữ liệu với các bộ lọc đã áp dụng
        return adminDashboardRepository.findFilteredBorrowIndexes(status, bookName, borrowDateTime, returnDateTime, pageable);
    }
    public List<Status> getAllStatuses() {
        return adminDashboardRepository.getAllStatus();
    }
    public List<Integer> getMonthlyBookLoanRequests() {
        return adminDashboardRepository.getMonthlyBookLoanRequests();
    }

    public List<Integer> getTotalBorrowCounts() {
        List<Object[]> results = adminDashboardRepository.getMonthlyBorrowStats();
        List<Integer> totalBorrowCounts = new ArrayList<>();

        for (Object[] row : results) {
            totalBorrowCounts.add((Integer) row[0]);
        }

        return totalBorrowCounts;
    }

    public List<Integer> getNumberOfBooksBorrowed() {
        List<Object[]> results = adminDashboardRepository.getMonthlyBorrowStats();
        List<Integer> numberOfBooksBorrowed = new ArrayList<>();

        for (Object[] row : results) {
            numberOfBooksBorrowed.add((Integer) row[1]);
        }

        return numberOfBooksBorrowed;
    }
}
