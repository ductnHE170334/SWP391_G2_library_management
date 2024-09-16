package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "News")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    private Date date_created;

    // Many-to-One relationship with Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")  // Foreign key to Staff
    private Staff staff;
}
