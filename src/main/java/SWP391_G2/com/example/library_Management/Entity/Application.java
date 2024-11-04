package SWP391_G2.com.example.library_Management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "response")
    private String response;

    // One-to-One relationship with Application Type
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_type_id")
    private Status application_type_id;

    // Many-to-One relationship with User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Foreign key to User
    private User user;
}
