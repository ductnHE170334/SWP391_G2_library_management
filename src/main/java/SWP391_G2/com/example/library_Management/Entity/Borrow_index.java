package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Borrow_index")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Borrow_index {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_condition_before")
    private String book_condition_before;

    @Column(name = "book_condition_after")
    private String book_condition_after;

    @Column(name = "borrow_date")
    private LocalDateTime borrow_date;

    @Column(name = "return_date")
    private LocalDateTime return_date;

    // One-to-One relationship with Status
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", unique = true)  // Foreign key to Book
    private Status status_id;

    // One-to-One relationship with Customer
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true)  // Foreign key to Book
    private Customer customer;

    // One-to-One relationship with Staff
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", unique = true)  // Foreign key to Book
    private Staff staff;

    //One-to-One relationship with Book_item
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_item_id", unique = true)  // Foreign key to Book_item
    private Book_item book_item_id;
}
