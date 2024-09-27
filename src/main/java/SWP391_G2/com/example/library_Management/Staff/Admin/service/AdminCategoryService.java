package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryService {

    @Autowired
    private AdminCategoryRepository adminCategoryRepository;

    // Constructor injection
    @Autowired
    public AdminCategoryService(AdminCategoryRepository theAdminCategoryRepository) {
        adminCategoryRepository = theAdminCategoryRepository;
    }

    // Get all categories
    public List<Category> getCategories() {
        return adminCategoryRepository.findAll();
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
    // Search for categories by name
    public List<Category> searchCategoriesByName(String name) {
        return adminCategoryRepository.findByNameContainingIgnoreCase(name);
    }

}
