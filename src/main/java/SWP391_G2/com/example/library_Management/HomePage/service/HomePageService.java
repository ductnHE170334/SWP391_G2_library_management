package SWP391_G2.com.example.library_Management.HomePage.service;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Entity.News;
import SWP391_G2.com.example.library_Management.HomePage.repository.HomePageAuthorRepository;
import SWP391_G2.com.example.library_Management.HomePage.repository.HomePageBookRepository;
import SWP391_G2.com.example.library_Management.HomePage.repository.HomePageCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Book> getAllBooks(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return  homePageBookRepository.findAll(pageable);
    }

    public Page<Book> findBookByName(String title, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return homePageBookRepository.findBookByName(title, pageable);
    }

    public List<Book> getTop3Books() {
        Pageable topThree = PageRequest.of(0, 3);
        return homePageBookRepository.findAll(topThree).getContent();
    }

    //Get book by id
    public Book getBook(int id) {
        return homePageBookRepository.findById(id).get();
    }
}
