package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/dashboard/category")
public class AdminCategoryManagementController {

    private final AdminCategoryService adminCategoryService;

    // Constructor injection for adminCategoryService
    public AdminCategoryManagementController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    // Get all categories and return the view list_category.html with pagination
    @GetMapping("/list")
    public String getCategory(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "6") int size,
                              Model model) {
        Page<Category> categories = adminCategoryService.getCategories(PageRequest.of(page, size));
        model.addAttribute("categories", categories.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categories.getTotalPages());
        return "Staff/dashboard/Category/list_category";
    }

    // Get a category by ID
    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable("id") String id, Model model) {
        Category category = adminCategoryService.getCategory(id);
        model.addAttribute("category", category);
        return "Staff/dashboard/Category/list_category";
    }

    // Hiển thị form chỉnh sửa danh mục
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        Category category = adminCategoryService.getCategory(id);
        model.addAttribute("category", category);
        return "Staff/dashboard/Category/update_category";
    }

    // Update category
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable String id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        adminCategoryService.updateCategory(id, name);
        redirectAttributes.addFlashAttribute("message", "Category with ID " + id + " has been updated successfully!");
        return "redirect:/admin/dashboard/category/list";
    }

    // Delete category
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            adminCategoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("message", "Category deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to delete category!");
        }
        return "redirect:/admin/dashboard/category/list";
    }

    // Hiển thị form thêm danh mục
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category()); // Khởi tạo đối tượng Category mới
        return "Staff/dashboard/Category/add_category"; // Trả về view cho form thêm danh mục
    }

    // Thêm danh mục
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        adminCategoryService.addCategory(category);
        redirectAttributes.addFlashAttribute("message", "Category added successfully!");
        return "redirect:/admin/dashboard/category/list";
    }

    // Tìm kiếm danh mục theo tên với phân trang
    @GetMapping("/search")
    public String searchCategories(@RequestParam("name") String name,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "6") int size,
                                   Model model) {
        Page<Category> categories = adminCategoryService.searchCategoriesByName(name, page, size);
        model.addAttribute("categories", categories.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categories.getTotalPages());
        if (categories.isEmpty()) {
            model.addAttribute("message", "No categories found for the search term: " + name);
        }
        return "Staff/dashboard/Category/list_category";
    }

}
