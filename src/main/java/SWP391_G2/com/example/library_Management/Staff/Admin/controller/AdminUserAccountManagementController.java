package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.Entity.Role;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminUserAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/dashboard/user")
public class AdminUserAccountManagementController {

    private final AdminUserAccountService adminUserAccountService;

    // Constructor injection
    public AdminUserAccountManagementController(AdminUserAccountService adminUserAccountService) {
        this.adminUserAccountService = adminUserAccountService;
    }

    // Get all user with pagination
    @GetMapping("/list")
    public String getUser(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "6") int size,
                           Model model) {
        Page<User> users = adminUserAccountService.getUser(PageRequest.of(page, size));
        model.addAttribute("users", users.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("roles", adminUserAccountService.getAllRoles());
        return "Staff/dashboard/Account/list_user";
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        User user = adminUserAccountService.getUser(String.valueOf(id));
        model.addAttribute("users", user);
        return "Staff/dashboard/Account/list_user";
    }

    // Show the update form for user
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = adminUserAccountService.getUser(String.valueOf(id));
        List<Role> roles = adminUserAccountService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "Staff/dashboard/Account/update_user";
    }

    // Update user information
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id,
                              @ModelAttribute User user,
                              @RequestParam("roleId") int roleId,
                              @RequestParam(value = "confirmPassword", required = false) String confirmPassword, // Get the confirm password from the form
                              RedirectAttributes redirectAttributes) {

        // Validate email format
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!user.getEmail().matches(emailRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid email format!");
            return "redirect:/admin/dashboard/user/update/" + id; // Redirect back to the update form
        }

        // Validate phone format (assuming phone should be numeric and 10-15 digits)
        String phoneRegex = "^\\d{10,15}$";
        if (!user.getPhone().matches(phoneRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid phone number format!");
            return "redirect:/admin/dashboard/user/update/" + id; // Redirect back to the update form
        }

        // Get all roles
        List<Role> roles = adminUserAccountService.getAllRoles();

        // Find the corresponding role based on roleId
        Role selectedRole = roles.stream()
                .filter(role -> role.getId() == roleId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));

        // Set the selected role for the staff member
        user.setRole(selectedRole);

        // Update staff in the database
        adminUserAccountService.updateUser(id, user);

        // Add success message
        redirectAttributes.addFlashAttribute("message", "User with ID " + id + " has been updated successfully!");
        return "redirect:/admin/dashboard/user/list";
    }



    // Delete a user by ID
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            adminUserAccountService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete user!");
        }
        return "redirect:/admin/dashboard/user/list";
    }

    // Show the add user form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", adminUserAccountService.getAllRoles());
        return "Staff/dashboard/Account/list_user";
    }

    // Add a new User
    @PostMapping("/list")
    public String addUser(@ModelAttribute User user,
                           @RequestParam int roleId,
                           @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                           RedirectAttributes redirectAttributes) {

        // Validate email format
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!user.getEmail().matches(emailRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid email format!");
            return "redirect:/admin/dashboard/user/list";
        }

        // Validate phone format
        String phoneRegex = "^\\d{10,15}$";
        if (!user.getPhone().matches(phoneRegex)) {
            redirectAttributes.addFlashAttribute("error", "Invalid phone number format!");
            return "redirect:/admin/dashboard/user/list";
        }

        // Check if passwords match
        if (user.getPassword() == null || !user.getPassword().equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
            return "redirect:/admin/dashboard/user/list";
        }

        List<Role> roles = adminUserAccountService.getAllRoles();
        Role selectedRole = roles.stream()
                .filter(role -> role.getId() == roleId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));

        user.setRole(selectedRole);
        adminUserAccountService.addUser(user);

        redirectAttributes.addFlashAttribute("message", "User added successfully!");
        return "redirect:/admin/dashboard/user/list";
    }



    // Search for user by name with pagination
    @GetMapping("/search")
    public String searchUser(@RequestParam("name") String name,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "6") int size,
                             Model model) {
        Page<User> users = adminUserAccountService.searchUserByName(name, page, size);
        model.addAttribute("users", users.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("roles", adminUserAccountService.getAllRoles());

        if (users.isEmpty()) {
            model.addAttribute("message", "No user found for the search term: " + name);
        }

        return "Staff/dashboard/Account/list_user";
    }
}
