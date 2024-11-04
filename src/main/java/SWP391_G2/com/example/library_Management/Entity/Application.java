package SWP391_G2.com.example.library_Management.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "status")
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime create_at = LocalDateTime.now();

    // One-to-One relationship with Application Type
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_type_id")
    private Application_Type application_type;

    // Many-to-One relationship with User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Foreign key to User
    private User user;
}
