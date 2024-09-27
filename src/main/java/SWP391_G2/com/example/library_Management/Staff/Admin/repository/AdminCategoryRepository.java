package SWP391_G2.com.example.library_Management.Staff.Admin.repository;

import SWP391_G2.com.example.library_Management.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByNameContainingIgnoreCase(String name);

}
