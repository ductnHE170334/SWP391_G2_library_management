package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Book_feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book_feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    private Date dateCreated;

    // One-to-One relationship with Book
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", unique = true)  // Foreign key to Book
    private Book book;

    // One-to-One relationship with Customer
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true)  // Foreign key to Customer
    private User customer;
}
