package SWP391_G2.com.example.library_Management.HomePage.service;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.HomePage.repository.HomePageAuthorRepository;
import SWP391_G2.com.example.library_Management.HomePage.repository.HomePageBookRepository;
import SWP391_G2.com.example.library_Management.HomePage.repository.HomePageCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {
    @Autowired
    private HomePageBookRepository homePageBookRepository;
    @Autowired
    private HomePageAuthorRepository homePageAuthorRepository;
    @Autowired
    private HomePageCategoryRepository homePageCategoryRepository;

    public List<Author> getAllAuthors() {
        return homePageAuthorRepository.findAll();
    }

    public List<Category> getAllCategories() {
        return homePageCategoryRepository.findAll();
    }

    public List<Book> getAllBooks(){ return  homePageBookRepository.findAll(); }
}
