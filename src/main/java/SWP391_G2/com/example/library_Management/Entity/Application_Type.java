package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Application_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Application_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "application_type")
    private String application_type;
}
