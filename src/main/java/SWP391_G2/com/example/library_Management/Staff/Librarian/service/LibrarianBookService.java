package SWP391_G2.com.example.library_Management.Staff.Librarian.service;


import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBookItemRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianBookService {

    @Autowired
    private LibrarianBookRepository repository;
    @Autowired
    private LibrarianBookItemRepository bookItemRepository;


    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book save(Book bo) {
        return repository.save(bo);
    }

    public Book getBookById(int bookId) {
        return repository.findById(bookId).get();
    }

    public List<Book_item> getBookItemsByBookId(int bookId) {
        return bookItemRepository.findByBookId(bookId);
    }
}
