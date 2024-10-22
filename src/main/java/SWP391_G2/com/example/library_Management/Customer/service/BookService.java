package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.BookRepository;
import SWP391_G2.com.example.library_Management.Customer.repository.IBookItemRepository;
import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService{
    private final BookRepository bookRepository;
    private final IBookItemRepository bookItemRepository;
    @Override
    public List<Book> getBooksCanBorrow() {
        return bookRepository.getBookCanBorrow();
    }

    @Override
    public Book_item getBookById(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        return bookItemRepository.getBook_itemByBook(book);
    }
}
