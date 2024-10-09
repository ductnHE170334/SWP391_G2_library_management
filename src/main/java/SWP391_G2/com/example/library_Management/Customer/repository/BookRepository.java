package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "Select * from Book bh where id not in (select bi.book_id from Borrow_history b join Book_item bi on bi.book_id = b.book_item_id)",nativeQuery = true)
    List<Book> getBookCanBorrow();
}
