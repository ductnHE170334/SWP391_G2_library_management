package SWP391_G2.com.example.library_Management.Staff.Admin.repository;

import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDashboardRepository extends JpaRepository<Borrow_index, Long> {

    @Query("SELECT COUNT(b) FROM BorrowIndex b WHERE b.statusId = (SELECT s.id FROM Status s WHERE s.status = 'Pending')")
    long getTotalPendingRequests();

    @Query("SELECT COUNT(b) FROM BorrowIndex b WHERE b.statusId = (SELECT s.id FROM Status s WHERE s.status = 'Rejected')")
    long getTotalRejectedRequests();

    @Query("SELECT COUNT(b) FROM BorrowIndex b WHERE b.statusId = (SELECT s.id FROM Status s WHERE s.status = 'Approved')")
    long getTotalApprovedRequests();

    @Query("SELECT COUNT(u) FROM User u WHERE u.roleId = (SELECT r.id FROM Role r WHERE r.name = 'customer')")
    long getTotalCustomers();

    @Query("SELECT SUM(b.quantity) FROM Book b")
    long getTotalBooks();
}
