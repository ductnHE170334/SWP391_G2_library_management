package SWP391_G2.com.example.library_Management.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "borrow_date")
    private LocalDateTime borrow_date;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "return_date")
    private LocalDateTime return_date;

    // Thêm trường created_requested_at
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "created_requested_at", nullable = false, updatable = false)
    private LocalDateTime created_requested_at = LocalDateTime.now();

    // One-to-One relationship with Status
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    // One-to-One relationship with Customer
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true)
    private User customer;

    // One-to-One relationship with Staff
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", unique = true)  // Foreign key to Book
    private User staff_id;

    // One-to-One relationship with Book_item
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_item_id", unique = true)
    private Book_item book_item;
}
