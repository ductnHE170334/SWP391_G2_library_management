package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Book_item;

import java.util.List;

public interface IBookService {
    public List<Book> getBooksCanBorrow();
    public Book_item getBookById(int id);
}
