package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class LibrarianStatusManagementController {
    @Autowired
    private LibrarianStatusService librarianStatusService;
}
