package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Entity.Status;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/status")
public class LibrarianStatusManagementController {

    @Autowired
    private LibrarianStatusService librarianStatusService;


    public LibrarianStatusManagementController(LibrarianStatusService theLibrarianStatusService) {
        librarianStatusService = theLibrarianStatusService;
    }


    @GetMapping("/list")
    public String getStatus(Model model) {
        List<Status> statuses = librarianStatusService.getStatus();
        model.addAttribute("statuses", statuses);
        return "test/list_status";
    }

    // Get a status by ID
    @GetMapping("/{id}")
    public String getStatusById(@PathVariable("id") String id, Model model) {
        Status status = librarianStatusService.getStatus(id);
        model.addAttribute("status", status);
        return "test/list_status";
    }


    // Show form to edit status
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        Status status = librarianStatusService.getStatus(id);
        model.addAttribute("status", status); // Add status to the model
        return "test/update_status"; // Return view for edit form
    }

    // Update status
    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable String id, @RequestParam String status, RedirectAttributes redirectAttributes) {
        librarianStatusService.updateStatus(id, status);
        redirectAttributes.addFlashAttribute("message", "Status with ID " + id + " has been updated successfully!");
        return "redirect:/status/list";
    }

    // Delete status
    @PostMapping("/{id}")
    public String deleteStatus(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            librarianStatusService.deleteStatus(id);
            redirectAttributes.addFlashAttribute("message", "Status deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to delete status!");
        }
        return "redirect:/status/list";
    }

    // Show form to add status
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("status", new Status()); // Initialize a new Status object
        return "test/add_status"; // Return view for add form
    }

    // Add status
    @PostMapping("/add")
    public String addStatus(@RequestParam String status, RedirectAttributes redirectAttributes) {
        librarianStatusService.addStatus(status);
        redirectAttributes.addFlashAttribute("message", "Status added successfully!");
        return "redirect:/status/list"; // Redirect to the list of statuses
    }
    // Search status by name
    @GetMapping("/search")
    public String searchStatus(@RequestParam("status") String status, Model model) {
        List<Status> statuses = librarianStatusService.searchStatusByName(status);
        model.addAttribute("statuses", statuses);
        if (statuses.isEmpty()) {
            model.addAttribute("message", "No statuses found for the search term: " + status);
        }
        return "test/list_status";
    }

}
