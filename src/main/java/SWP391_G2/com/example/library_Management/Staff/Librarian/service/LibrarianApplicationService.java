package SWP391_G2.com.example.library_Management.Staff.Librarian.service;

import SWP391_G2.com.example.library_Management.Entity.Application;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianApplicationService {
    @Autowired
    LibrarianApplicationRepository librarianApplicationRepository;
    public Page<Application> getApplications(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return librarianApplicationRepository.findAll(pageable);
    }}
