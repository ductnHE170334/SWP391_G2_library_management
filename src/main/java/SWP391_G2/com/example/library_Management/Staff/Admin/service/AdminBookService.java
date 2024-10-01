package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Entity.Publisher;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminAuthorRepository;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminBookRepository;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminCategoryRepository;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminPublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class AdminBookService {
    @Autowired
    private AdminBookRepository adminBookRepository;
    @Autowired
    private AdminCategoryRepository adminCategoryRepository;
    @Autowired
    private AdminAuthorRepository adminAuthorRepository;
    @Autowired
    private AdminPublisherRepository adminPublisherRepository;
//    public List<Book> getAllBooks() {
//        return adminBookRepository.findAll();
//    }
public Page<Book> getAllBooksPaginated(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber - 1, pageSize); // PageRequest is zero-based, so subtract 1
    return adminBookRepository.findAll(pageable);
}

    public List<Category> getAllCategories() {
        return adminCategoryRepository.findAll();
    }

    public List<Author> getAllAuthors() {
        return adminAuthorRepository.findAll();
    }

    public List<Publisher> getAllPublisher() {
        return adminPublisherRepository.findAll();
    }

    public void save(Book book) {
        adminBookRepository.save(book);
    }


    @Transactional()
    public Book getBookById(int id) {

        return adminBookRepository.findById(id).get();
    }

    public void update(Book book) {

        Optional<Book> existingBookOptional = adminBookRepository.findById(book.getId());
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();


            existingBook.setName(book.getName());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setDescription(book.getDescription());
            existingBook.setCategories(book.getCategories());
            existingBook.setAuthors(book.getAuthors());

            adminBookRepository.save(existingBook);
        } else {
            throw new EntityNotFoundException("Book not found with ID: " + book.getId());
        }
    }

    @Transactional
    public void deleteBook(int id) {
        if (adminBookRepository.existsById(id)) {

            Book book = adminBookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));

            // Find category and author by book and remove it
            book.getCategories().forEach(category -> category.getBooks().remove(book));
            book.getAuthors().forEach(author -> author.getBooks().remove(book));

            // Delete category and author
            book.getCategories().clear();
            book.getAuthors().clear();

            // save now in database
            adminBookRepository.saveAndFlush(book);


            adminBookRepository.delete(book);
            adminBookRepository.flush(); // save again

            // Check in console if the book delete success
            boolean existsAfterDelete = adminBookRepository.existsById(id);
            if (!existsAfterDelete) {
                System.out.println("Book with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Failed to delete book with ID " + id + ".");
            }
        } else {
            throw new EntityNotFoundException("Book not found with ID: " + id);
        }
    }


    public boolean existsByName(String name) {
        return adminBookRepository.findByName(name) != null;
    }
}
