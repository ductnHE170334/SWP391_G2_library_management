package SWP391_G2.com.example.library_Management.Staff.Librarian.service;


import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrarianStatusService {
    @Autowired
    private LibrarianStatusRepository librarianStatusRepository;
}
