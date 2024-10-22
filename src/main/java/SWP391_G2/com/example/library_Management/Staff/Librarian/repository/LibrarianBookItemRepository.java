package SWP391_G2.com.example.library_Management.Staff.Librarian.repository;


import SWP391_G2.com.example.library_Management.Entity.Book_item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibrarianBookItemRepository extends JpaRepository<Book_item,String> {
    List<Book_item> findByBookId(int bookId);
}
