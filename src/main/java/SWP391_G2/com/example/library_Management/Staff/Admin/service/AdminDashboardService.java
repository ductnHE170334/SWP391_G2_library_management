package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
