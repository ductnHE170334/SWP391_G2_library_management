package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Entity.Publisher;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminAuthorRepository;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//Spring MVC
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AdminAuthorManagementController {
    private AdminAuthorRepository authorRepository;

    @Autowired
    public AdminAuthorManagementController(AdminAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping({"", "/"})
    public String getAllAuthors(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Author> authorPage;

        if (search != null && !search.isEmpty()) {
            authorPage = authorRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            authorPage = authorRepository.findAll(pageable);
        }

        model.addAttribute("authorPage", authorPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", authorPage.getTotalPages());
        model.addAttribute("search", search);  // Make sure this is included
        return "Staff/dashboard/Author/list";  // Ensure this path is correct
    }


    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") int id, Model model) {
        Author author = authorRepository.findById(id).get();
        if (author!=null){
            authorRepository.delete(author);
            return "redirect:/authors";
        }
        return "redirect:/authors";
    }

    @GetMapping("/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "staff/dashboard/author/create"; // This should match the name of your HTML file for adding an author
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        authorRepository.save(author);
        redirectAttributes.addFlashAttribute("message", "Author added successfully!");
        return "redirect:/authors"; // Redirect to the list of authors
    }

    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable("id") int id, Model model) {
        Author author = authorRepository.findById(id).get();
        model.addAttribute("author", author);
        return "staff/dashboard/author/edit";
    }

    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        authorRepository.save(author);
        redirectAttributes.addFlashAttribute("message", "Author updated successfully!");
        return "redirect:/authors"; // Redirect to the list of authors
    }
}
