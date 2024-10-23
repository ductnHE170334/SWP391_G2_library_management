package SWP391_G2.com.example.library_Management.Entity;

import SWP391_G2.com.example.library_Management.Customer.Enum.BorrowRequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Borrow_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Borrow_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "borrow_date")
    private Date borrow_date;

    @Column(name = "return_date")
    private Date return_date;

    // One-to-One relationship with Book_item
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_item_id", unique = true)  // Foreign key to Book
    private Book_item book_item;

    // One-to-One relationship with Customer
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true)  // Foreign key to Customer
    private User customer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BorrowRequestStatus status;

}
