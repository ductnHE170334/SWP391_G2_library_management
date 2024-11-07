package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Application;
import SWP391_G2.com.example.library_Management.Entity.Application_Type;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/librarian/application")
public class LibrarianApplicationController {
    @Autowired
    LibrarianApplicationService librarianApplicationService;
    @GetMapping("/list")
    public String listApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            Model model) {

        Page<Application> applicationPage = librarianApplicationService.getApplications(page, size);
        model.addAttribute("applications", applicationPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", applicationPage.getTotalPages());
        return "Staff/dashboard/Application/listApplication";
    }
    @GetMapping("/viewApplicationDetail/{id}")
    public String viewApplicationDetail(@PathVariable("id") Integer id, Model model) {
        Application application = librarianApplicationService.getApplicationById(id);
        List<Application_Type> applicationTypes = librarianApplicationService.getAllApplicationTypes();

        System.out.println("======TEST DATA=====");
        System.out.println(application.getApplication_type().getApplication_type());
        model.addAttribute("applicationTypes", applicationTypes);
        model.addAttribute("application", application);
        return "Staff/dashboard/Application/viewApplicationDetail";
    }
    @PostMapping("/save")
    public String saveApplication(@ModelAttribute("application") Application application,

                                  RedirectAttributes redirectAttributes) {





        // Save the updated application
        librarianApplicationService.update(application);

        // Add success message
        redirectAttributes.addFlashAttribute("successMessage", "Application has been saved successfully.");

        return "redirect:/librarian/application/list";
    }
}
