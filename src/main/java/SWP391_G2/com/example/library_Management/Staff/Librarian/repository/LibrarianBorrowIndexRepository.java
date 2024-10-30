package SWP391_G2.com.example.library_Management.Staff.Librarian.repository;

import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibrarianBorrowIndexRepository extends JpaRepository<Borrow_index,Long> {
    Page<Borrow_index> findAll(Pageable pageable);

    List<Borrow_index> findByCustomer(User customer);

    @Query("SELECT b FROM Borrow_index b WHERE b.status.id = :statusId")
    Page<Borrow_index> findByStatusId(@Param("statusId") int statusId, Pageable pageable);

    @Query("SELECT b FROM Borrow_index b WHERE b.status.id <> :statusId")
    Page<Borrow_index> findByStatusIdNot(@Param("statusId") int statusId, Pageable pageable);
}
