package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    // Constructor injection
    @Autowired
    public AdminCategoryService(AdminCategoryRepository adminCategoryRepository) {
        this.adminCategoryRepository = adminCategoryRepository;
    }

    // Get all categories with pagination
    public Page<Category> getCategories(Pageable pageable) {
        return adminCategoryRepository.findAll(pageable);
    }

    // Get a specific category by ID
    public Category getCategory(String id) {
        return adminCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Update a category's name
    public Category updateCategory(String id, String name) {
        Category category = getCategory(id);
        category.setName(name);
        return adminCategoryRepository.save(category);
    }

    // Delete a category by ID
    public void deleteCategory(String id) {
        adminCategoryRepository.deleteById(id);
    }

    // Add a new category
    public Category addCategory(Category category) {
        return adminCategoryRepository.save(category);
    }

    // Search for categories by name with pagination
    public Page<Category> searchCategoriesByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return adminCategoryRepository.findByNameContainingIgnoreCase(name, pageable);
    }

}
