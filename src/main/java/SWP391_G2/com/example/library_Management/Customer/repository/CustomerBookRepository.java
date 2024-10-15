package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerBookRepository extends JpaRepository<Book, String> {

}
