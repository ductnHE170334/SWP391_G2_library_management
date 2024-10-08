package SWP391_G2.com.example.library_Management.Staff.Admin.repository;

import SWP391_G2.com.example.library_Management.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminCategoryRepository extends JpaRepository<Category, String> {
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
