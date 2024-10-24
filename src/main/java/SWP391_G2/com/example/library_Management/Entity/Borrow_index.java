package SWP391_G2.com.example.library_Management.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "Borrow_index")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow_index {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_condition_before")
    private String bookConditionBefore;

    @Column(name = "book_condition_after")
    private String bookConditionAfter;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "created_requested_at", nullable = false, updatable = false)
    private LocalDateTime createdRequestedAt = LocalDateTime.now();

    // Many-to-One relationship with User (Staff)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "id") // Pointing to the `id` column of the User table
    private User staff;

    // Many-to-One relationship with User (Customer)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id") // Pointing to the `id` column of the User table
    private User customer;

    // One-to-One relationship with Status
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    // One-to-One relationship with Book_item
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_item_id", unique = true)
    private Book_item book_item;
}



