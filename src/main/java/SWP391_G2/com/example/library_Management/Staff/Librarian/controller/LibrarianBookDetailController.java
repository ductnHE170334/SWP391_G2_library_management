package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/librarian/bookDetail")
public class LibrarianBookDetailController {
    @Autowired
    LibrarianBookService librarianBookService;
    @GetMapping("/{bookId}")
    public String getBookDetails(@PathVariable int bookId, Model model) {
        Book book = librarianBookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "Staff/dashboard/Borrow_request/BookDetail";
    }
}
