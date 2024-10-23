package SWP391_G2.com.example.library_Management.Staff.Librarian.repository;

import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianCustomerRepository extends JpaRepository<Customer, Long> {
}
