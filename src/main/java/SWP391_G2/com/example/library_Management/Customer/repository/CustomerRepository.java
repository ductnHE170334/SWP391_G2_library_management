package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findOneByEmailAndPassword(String email, String password);
    Customer findByEmail(String email);
}