package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Book_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book_image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String image_url;

    // Many-to-One relationship with Book
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")  // Foreign key to Publisher
    private Book book;
}
