package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminAuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthorService {
    @Autowired
    private AdminAuthorRepository adminAuthorRepository;

    public Author findById(int id) {
        Author author = adminAuthorRepository.findById(id).get();
        return author;
    }
}
