package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerBorrowIndexRepository extends JpaRepository<Borrow_index, Integer> {
    // Find by customer (not user)
    List<Borrow_index> findByCustomer(User customer);

    // Custom query using customer field
    @Query("SELECT b FROM Borrow_index b WHERE b.customer = :customer AND b.book_item = :bookItem")
    Borrow_index findByCustomerAndBookItem(@Param("customer") User customer, @Param("bookItem") Book_item bookItem);
}


