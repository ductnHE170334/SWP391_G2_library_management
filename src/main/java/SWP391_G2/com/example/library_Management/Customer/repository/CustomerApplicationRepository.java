package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerApplicationRepository extends JpaRepository<Application,Integer> {
    List<Application> findByUser_Id(Long userId);
}
