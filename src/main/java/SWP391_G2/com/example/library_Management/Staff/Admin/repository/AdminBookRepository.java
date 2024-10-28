package SWP391_G2.com.example.library_Management.Staff.Admin.repository;

import SWP391_G2.com.example.library_Management.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminBookRepository extends JpaRepository<Book, Integer> {
    Book findByName(String name); // Hoặc sử dụng Optional<Book> nếu cần
}
