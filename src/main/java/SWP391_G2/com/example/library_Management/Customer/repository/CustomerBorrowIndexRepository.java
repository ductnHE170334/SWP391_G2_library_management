package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerBorrowIndexRepository extends JpaRepository<Borrow_index, String> {
    List<Borrow_index> findByCustomer(Customer customer);

    @Query("SELECT b FROM Borrow_index b WHERE b.customer = :customer AND b.book_item = :bookItem")
    Borrow_index findByCustomerAndBook_item(@Param("customer") Customer customer, @Param("bookItem") Book_item bookItem);


}


