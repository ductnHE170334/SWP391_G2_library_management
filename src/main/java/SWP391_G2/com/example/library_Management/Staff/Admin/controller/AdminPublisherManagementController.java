package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Entity.Publisher;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminPublisherRepository;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/publishers")
public class AdminPublisherManagementController {
    private AdminPublisherRepository publisherRepository;
    @Autowired
    public AdminPublisherManagementController(AdminPublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }



    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") int id, Model model) {
        Publisher publisher = publisherRepository.findById(id).get();
        if (publisher!=null){
            publisherRepository.delete(publisher);
            return "redirect:/publishers";
        }
        return "redirect:/publishers";
    }
    // Display list of publishers with pagination and search
    @GetMapping({"", "/"})
    public String getAllPublishers(Model model,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Publisher> publisherPage;

        if (search != null && !search.isEmpty()) {
            publisherPage = publisherRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            publisherPage = publisherRepository.findAll(pageable);
        }

        model.addAttribute("publisherPage", publisherPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", publisherPage.getTotalPages());
        model.addAttribute("search", search);
        return "Staff/dashboard/Publisher/list"; // Ensure this path is correct
    }

    @GetMapping("/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "staff/dashboard/publisher/create"; // This should match the name of your HTML file for adding an author
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Publisher publisher, RedirectAttributes redirectAttributes) {
        publisherRepository.save(publisher);
        redirectAttributes.addFlashAttribute("message", "publisher added successfully!");
        return "redirect:/publishers"; // Redirect to the list of authors
    }

    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable("id") int id, Model model) {
        Publisher publisher = publisherRepository.findById(id).get();
        model.addAttribute("publisher", publisher);
        return "staff/dashboard/publisher/edit";
    }

    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute Publisher publisher, RedirectAttributes redirectAttributes) {
        publisherRepository.save(publisher);
        redirectAttributes.addFlashAttribute("message", "publisher updated successfully!");
        return "redirect:/publishers"; // Redirect to the list of authors
    }
}
