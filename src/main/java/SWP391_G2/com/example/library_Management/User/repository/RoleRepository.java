package SWP391_G2.com.example.library_Management.User.repository;

import SWP391_G2.com.example.library_Management.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
