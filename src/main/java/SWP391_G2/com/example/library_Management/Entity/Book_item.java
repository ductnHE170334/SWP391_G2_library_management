package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Book_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "condition")
    private String condition;

    // One-to-One relationship with Customer
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", unique = true)  // Foreign key to Customer
    private Book book;
}
