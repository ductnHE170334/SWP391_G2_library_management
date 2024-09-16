package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
