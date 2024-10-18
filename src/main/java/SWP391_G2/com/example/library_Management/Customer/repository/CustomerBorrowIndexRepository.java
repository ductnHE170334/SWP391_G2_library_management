package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerBorrowIndexRepository extends JpaRepository<Borrow_index, String> {
    List<Borrow_index> findByCustomer(Customer customer);
}
