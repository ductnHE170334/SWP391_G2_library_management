//package SWP391_G2.com.example.library_Management.Staff.Admin.controller;
//
//import SWP391_G2.com.example.library_Management.Entity.Staff;
//import SWP391_G2.com.example.library_Management.Entity.Role; // Assuming you have a Role entity
//import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminStaffAccountService;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/staff")
//public class AdminStaffAccountManagementController {
//
//    private final AdminStaffAccountService adminStaffAccountService;
//
//    // Constructor injection
//    public AdminStaffAccountManagementController(AdminStaffAccountService adminStaffAccountService) {
//        this.adminStaffAccountService = adminStaffAccountService;
//    }
//
//    // Get all staff with pagination
//    @GetMapping("/list")
//    public String getStaff(@RequestParam(defaultValue = "0") int page,
//                           @RequestParam(defaultValue = "6") int size,
//                           Model model) {
//        Page<Staff> staff = adminStaffAccountService.getStaff(PageRequest.of(page, size));
//        model.addAttribute("staffs", staff.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", staff.getTotalPages());
//        model.addAttribute("roles", adminStaffAccountService.getAllRoles());
//        return "Staff/dashboard/Account/list_staff";
//    }
//
//    // Get a staff by ID
//    @GetMapping("/{id}")
//    public String getStaffById(@PathVariable("id") int id, Model model) {
//        Staff staff = adminStaffAccountService.getStaff(String.valueOf(id));
//        model.addAttribute("staff", staff);
//        return "Staff/dashboard/Account/list_staff";
//    }
//
//    // Show the update form for staff
//    @GetMapping("/update/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        Staff staff = adminStaffAccountService.getStaff(String.valueOf(id));
//        List<Role> roles = adminStaffAccountService.getAllRoles();
//        model.addAttribute("staff", staff);
//        model.addAttribute("roles", roles);
//        return "Staff/dashboard/Account/update_staff";
//    }
//
//    // Update staff information
//    @PostMapping("/update/{id}")
//    public String updateStaff(@PathVariable("id") int id,
//                              @ModelAttribute Staff staff,
//                              @RequestParam("roleId") int roleId,  // Get the roleId from the form
//                              @RequestParam(value = "confirmPassword", required = false) String confirmPassword, // Get the confirm password from the form
//                              RedirectAttributes redirectAttributes) {
//
//        // Validate email format
//        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
//        if (!staff.getEmail().matches(emailRegex)) {
//            redirectAttributes.addFlashAttribute("error", "Invalid email format!");
//            return "redirect:/staff/update/" + id; // Redirect back to the update form
//        }
//
//        // Validate phone format (assuming phone should be numeric and 10-15 digits)
//        String phoneRegex = "^\\d{10,15}$";
//        if (!staff.getPhone().matches(phoneRegex)) {
//            redirectAttributes.addFlashAttribute("error", "Invalid phone number format!");
//            return "redirect:/staff/update/" + id; // Redirect back to the update form
//        }
//
//        // Get all roles
//        List<Role> roles = adminStaffAccountService.getAllRoles();
//
//        // Find the corresponding role based on roleId
//        Role selectedRole = roles.stream()
//                .filter(role -> role.getId() == roleId)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));
//
//        // Set the selected role for the staff member
//        staff.setRole(selectedRole);
//
//        // Update staff in the database
//        adminStaffAccountService.updateStaff(id, staff);
//
//        // Add success message
//        redirectAttributes.addFlashAttribute("message", "Staff with ID " + id + " has been updated successfully!");
//        return "redirect:/staff/list";
//    }
//
//
//
//    // Delete a staff by ID
//    @PostMapping("/delete/{id}")
//    public String deleteStaff(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
//        try {
//            adminStaffAccountService.deleteStaff(id);
//            redirectAttributes.addFlashAttribute("message", "Staff deleted successfully!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Failed to delete staff!");
//        }
//        return "redirect:/staff/list";
//    }
//
//    // Show the add staff form
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("staff", new Staff());
//        model.addAttribute("roles", adminStaffAccountService.getAllRoles());
//        return "Staff/dashboard/Account/list_staff";
//    }
//
//    // Add a new staff
//    @PostMapping("/list")
//    public String addStaff(@ModelAttribute Staff staff,
//                           @RequestParam int roleId,
//                           @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
//                           RedirectAttributes redirectAttributes) {
//
//        // Validate email format
//        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
//        if (!staff.getEmail().matches(emailRegex)) {
//            redirectAttributes.addFlashAttribute("error", "Invalid email format!");
//            return "redirect:/staff/list";
//        }
//
//        // Validate phone format
//        String phoneRegex = "^\\d{10,15}$";
//        if (!staff.getPhone().matches(phoneRegex)) {
//            redirectAttributes.addFlashAttribute("error", "Invalid phone number format!");
//            return "redirect:/staff/list";
//        }
//
//        // Check if passwords match
//        if (staff.getPassword() == null || !staff.getPassword().equals(confirmPassword)) {
//            redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
//            return "redirect:/staff/list";
//        }
//
//        List<Role> roles = adminStaffAccountService.getAllRoles();
//        Role selectedRole = roles.stream()
//                .filter(role -> role.getId() == roleId)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));
//
//        staff.setRole(selectedRole);
//        adminStaffAccountService.addStaff(staff);
//
//        redirectAttributes.addFlashAttribute("message", "Staff added successfully!");
//        return "redirect:/staff/list";
//    }
//
//
//
//    // Search for staff by name with pagination
//    @GetMapping("/search")
//    public String searchStaff(@RequestParam("name") String name,
//                              @RequestParam(defaultValue = "0") int page,
//                              @RequestParam(defaultValue = "6") int size,
//                              Model model) {
//        Page<Staff> staff = adminStaffAccountService.searchStaffByName(name, page, size);
//        model.addAttribute("staffs", staff.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", staff.getTotalPages());
//        if (staff.isEmpty()) {
//            model.addAttribute("message", "No staff found for the search term: " + name);
//        }
//        return "Staff/dashboard/Account/list_staff";
//    }
//}
