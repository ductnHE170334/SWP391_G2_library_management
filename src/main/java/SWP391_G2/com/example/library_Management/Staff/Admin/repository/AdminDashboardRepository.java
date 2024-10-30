package SWP391_G2.com.example.library_Management.Staff.Admin.repository;

import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Status;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDashboardRepository extends JpaRepository<Borrow_index, Long> {

    @Query("SELECT COUNT(b) FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Pending')")
    long getTotalPendingRequests();

    @Query("SELECT COUNT(b) FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Rejected')")
    long getTotalRejectedRequests();

    @Query("SELECT COUNT(b) FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Approved')")
    long getTotalApprovedRequests();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role.id = (SELECT r.id FROM Role r WHERE r.name = 'customer')")
    long getTotalCustomers();

    @Query("SELECT COUNT(b.id) FROM Book b")
    long getTotalBooks();

    // Lấy danh sách tất cả sách
    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();

    // Lấy danh sách tất cả người dùng
    @Query("SELECT u FROM User u")
    List<User> getAllUsers();

    // Lấy danh sách tất cả các bản ghi Borrow_index
    @Query("SELECT b FROM Borrow_index b")
    List<Borrow_index> getAllBorrowIndexes();

    // Lấy danh sách tất cả các status
    @Query("SELECT s FROM Status s") // Sửa tên entity ở đây
    List<Status> getAllStatus(); // Sửa kiểu trả về


    // Thống kê số lượng sách đã được mượn
    @Query("SELECT COUNT(b) FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Approved')")
    long getTotalBorrowedBooks();

    // Lấy tất cả các bản ghi với trạng thái 'Pending'
    @Query("SELECT b FROM Borrow_index b WHERE b.status.id = 1")
    List<Borrow_index> getPendingBorrowIndexes();

    // Lấy tất cả các bản ghi với trạng thái 'Rejected'
    @Query("SELECT b FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Rejected')")
    List<Borrow_index> getRejectedBorrowIndexes();

    // Lấy danh sách tất cả sách đã được mượn kèm trạng thái là 'Approved'
    @Query("SELECT b FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Approved')")
    List<Borrow_index> getApprovedBorrowIndexes();

    // Lấy danh sách người dùng theo vai trò
    @Query("SELECT u FROM User u WHERE u.role.name = :roleName")
    List<User> getUsersByRole(String roleName);

}
