package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookItemRepository extends JpaRepository<Book_item,Integer> {
    public Book_item getBook_itemByBook(Book book);
}
