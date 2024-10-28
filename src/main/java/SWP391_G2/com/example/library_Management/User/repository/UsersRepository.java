package SWP391_G2.com.example.library_Management.User.repository;

import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> { // Sửa String thành Integer
    Optional<User> findByEmail(String email);
}
