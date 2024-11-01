package SWP391_G2.com.example.library_Management.Staff.Admin.repository;

import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Status;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    Page<Borrow_index> getPendingBorrowIndexes(Pageable pageable);

    // Lấy tất cả các bản ghi với trạng thái 'Rejected'
    @Query("SELECT b FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Rejected')")
    List<Borrow_index> getRejectedBorrowIndexes();

    // Lấy danh sách tất cả sách đã được mượn kèm trạng thái là 'Approved'
    @Query("SELECT b FROM Borrow_index b WHERE b.status.id = (SELECT s.id FROM Status s WHERE s.status = 'Approved')")
    List<Borrow_index> getApprovedBorrowIndexes();

    // Lấy danh sách người dùng theo vai trò
    @Query("SELECT u FROM User u WHERE u.role.name = :roleName")
    List<User> getUsersByRole(String roleName);

    @Query("SELECT b FROM Borrow_index b WHERE " +
            "(:status IS NULL OR :status = 'All' OR b.status.status = :status) AND " +
            "(:bookName IS NULL OR b.book_item.book.name LIKE %:bookName%) AND " +
            "(:startDate IS NULL OR b.borrowDate >= :startDate) AND " +
            "(:endDate IS NULL OR b.returnDate <= :endDate)")
    Page<Borrow_index> findFilteredBorrowIndexes(@Param("status") String status,
                                                 @Param("bookName") String bookName,
                                                 @Param("startDate") LocalDateTime startDate,
                                                 @Param("endDate") LocalDateTime endDate,
                                                 Pageable pageable);
    @Query(value = "WITH Months AS ( " +
            "    SELECT 1 AS Month " +
            "    UNION ALL SELECT 2 " +
            "    UNION ALL SELECT 3 " +
            "    UNION ALL SELECT 4 " +
            "    UNION ALL SELECT 5 " +
            "    UNION ALL SELECT 6 " +
            "    UNION ALL SELECT 7 " +
            "    UNION ALL SELECT 8 " +
            "    UNION ALL SELECT 9 " +
            "    UNION ALL SELECT 10 " +
            "    UNION ALL SELECT 11 " +
            "    UNION ALL SELECT 12 " +
            ") " +
            "SELECT COUNT(b.borrow_date) " +
            "FROM Months m " +
            "LEFT JOIN library_management.dbo.Borrow_index b ON MONTH(b.borrow_date) = m.Month " +
            "GROUP BY m.Month " +
            "ORDER BY m.Month", nativeQuery = true)
    List<Integer> getMonthlyBookLoanRequests();

    // Thêm truy vấn mới để lấy số lượng sách đã mượn theo tháng
    @Query(value = "WITH Months AS ( " +
            "    SELECT 1 AS Month " +
            "    UNION ALL SELECT 2 " +
            "    UNION ALL SELECT 3 " +
            "    UNION ALL SELECT 4 " +
            "    UNION ALL SELECT 5 " +
            "    UNION ALL SELECT 6 " +
            "    UNION ALL SELECT 7 " +
            "    UNION ALL SELECT 8 " +
            "    UNION ALL SELECT 9 " +
            "    UNION ALL SELECT 10 " +
            "    UNION ALL SELECT 11 " +
            "    UNION ALL SELECT 12 " +
            ") " +
            "SELECT COALESCE(bc.TotalBorrowCount, 0) AS TotalBorrowCount, " +
            "       COALESCE(dbc.NumberOfBooksBorrowed, 0) AS NumberOfBooksBorrowed " +
            "FROM Months m " +
            "LEFT JOIN ( " +
            "    SELECT MONTH(borrow_date) AS MonthNumber, COUNT(*) AS TotalBorrowCount " +
            "    FROM Borrow_index " +
            "    WHERE status_id = 1 " +
            "    GROUP BY MONTH(borrow_date) " +
            ") bc ON m.Month = bc.MonthNumber " +
            "LEFT JOIN ( " +
            "    SELECT MONTH(borrow_date) AS MonthNumber, COUNT(DISTINCT book_item_id) AS NumberOfBooksBorrowed " +
            "    FROM Borrow_index " +
            "    WHERE status_id = 1 " +
            "    GROUP BY MONTH(borrow_date) " +
            ") dbc ON m.Month = dbc.MonthNumber " +
            "ORDER BY m.Month", nativeQuery = true)
    List<Object[]> getMonthlyBorrowStats();

}
