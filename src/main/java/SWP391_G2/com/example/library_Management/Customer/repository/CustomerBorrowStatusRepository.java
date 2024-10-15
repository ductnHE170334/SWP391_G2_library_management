package SWP391_G2.com.example.library_Management.Customer.repository;


import SWP391_G2.com.example.library_Management.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerBorrowStatusRepository extends JpaRepository<Status, String> {
}
