package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.Status;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return adminDashboardRepository.getPendingBorrowIndexes();
    }
    public List<Borrow_index> getRejectedBorrowIndexes() {
        return adminDashboardRepository.getRejectedBorrowIndexes();
    }
    public List<Borrow_index> getApprovedBorrowIndexes() {
        return adminDashboardRepository.getApprovedBorrowIndexes();
    }
    public List<Borrow_index> getAllBorrowIndexes() {
        return adminDashboardRepository.getAllBorrowIndexes();
    }
    public List<Status> getAllStatuses() {
        return adminDashboardRepository.getAllStatus();
    }
}
