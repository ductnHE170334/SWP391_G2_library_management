package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Entity.Publisher;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminBookService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;

import java.util.List;

@Controller
@RequestMapping("/books")
public class AdminBookManagementController {

    @Autowired
    private AdminBookService adminBookService;

//    @GetMapping
//    public String listBook(Model theModel) {
//        List<Book> books = adminBookService.getAllBooks();
//        theModel.addAttribute("books", books);
//        return "Staff/dashboard/Book/ManageBook";
//    }
@GetMapping
public String listBook(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "size", defaultValue = "4") int size,
                       Model theModel) {
    Page<Book> bookPage = adminBookService.getAllBooksPaginated(page, size);
    theModel.addAttribute("books", bookPage.getContent());
    theModel.addAttribute("currentPage", page);
    theModel.addAttribute("totalPages", bookPage.getTotalPages());
    return "Staff/dashboard/Book/ManageBook";
}
    @GetMapping("/addBook")
    public String viewAddBook(Model theModel){
        Book book = new Book();
        List<Category> categories = adminBookService.getAllCategories();
        List<Author> authors = adminBookService.getAllAuthors();
        List<Publisher> publishers = adminBookService.getAllPublisher();
        theModel.addAttribute("categories",categories);
        theModel.addAttribute("authors",authors);
        theModel.addAttribute("publishers",publishers);
        theModel.addAttribute("book",book);
        return "Staff/dashboard/Book/AddBook";
    }
    //show edit book by id web
    @GetMapping("/editBook/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        // Find the book by ID
        Book book = adminBookService.getBookById(id);
        System.out.println(book.getCategories());
        System.out.println(book.getAuthors());
        Hibernate.initialize(book.getCategories());
        Hibernate.initialize(book.getAuthors());


        if (book == null) {
            // Handle book not found
            return "redirect:/error";
        }

        // Load all categories and authors
        List<Category> categories = adminBookService.getAllCategories();
        System.out.println(categories);
        List<Author> authors = adminBookService.getAllAuthors();
        List<Publisher> publishers = adminBookService.getAllPublisher();

        // Pass the data to the view
        model.addAttribute("book", book);
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers",publishers);

        return "Staff/dashboard/Book/EditBook";
    }

    //Delete book by id
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id) {
        System.out.println("==============================");
        System.out.println("The hibrenate start delete:");
        adminBookService.deleteBook(id);
        System.out.println("Deleted book with ID: " + id);
        System.out.println("==============================");
        return "redirect:/books";
    }


    @PostMapping("/save")
    public String addBook(@ModelAttribute("book") Book book, Model model) {
        System.out.println(book.getId());
        // Kiểm tra xem có phải là thêm sách mới không
        if (book.getId() == 0) { // Nếu ID là 0, tức là thêm mới
            if (adminBookService.existsByName(book.getName())) {
                Book book1 = new Book();
                List<Category> categories = adminBookService.getAllCategories();
                List<Author> authors = adminBookService.getAllAuthors();
                List<Publisher> publishers = adminBookService.getAllPublisher();
                model.addAttribute("categories",categories);
                model.addAttribute("authors",authors);
                model.addAttribute("publishers",publishers);
                model.addAttribute("book",book1);
                model.addAttribute("errorMessage", "A book with the same name already exists.");
                return "Staff/dashboard/Book/EditBook"; //
            }
        }

        if (book.getId() != 0) {
            adminBookService.update(book); // Cập nhật sách
        } else {
            adminBookService.save(book); // Thêm sách mới
        }
        return "redirect:/books";
    }
}
