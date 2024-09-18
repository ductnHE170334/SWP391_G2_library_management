package SWP391_G2.com.example.library_Management.Staff.Admin.repository;


import SWP391_G2.com.example.library_Management.Entity.Book_item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminBookItemRepository extends JpaRepository<Book_item,String> {
}
