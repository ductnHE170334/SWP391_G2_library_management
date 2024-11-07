package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Application;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
