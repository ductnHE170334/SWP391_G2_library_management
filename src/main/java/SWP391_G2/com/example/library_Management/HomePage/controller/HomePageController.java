package SWP391_G2.com.example.library_Management.HomePage.controller;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Category;
import SWP391_G2.com.example.library_Management.Entity.News;
import SWP391_G2.com.example.library_Management.HomePage.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    //Get all information for the home page
    @GetMapping("/list")
    public String homePage(Model theModel, @Param("title") String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Book> booksPage;

        if (title != null) {
            booksPage = homePageService.findBookByName(title, page, size);
            theModel.addAttribute("searchtitle", title);
        } else {
            booksPage = homePageService.getAllBooks(page, size);
        }

        //get the author from the service
        List<Author> theAuthors = homePageService.getAllAuthors();

        //get all categories from the service
        List<Category> theCategories = homePageService.getAllCategories();

        //get top 3 books from the service
        List<Book> top3Books = homePageService.getTop3Books();

        // add to the spring model
        theModel.addAttribute("authors", theAuthors);
        theModel.addAttribute("categories", theCategories);
        theModel.addAttribute("booksPage", booksPage);
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", booksPage.getTotalPages());
        theModel.addAttribute("top3Books", top3Books);

        return "Customer/HomePage/HomePage";
    }

    //Get book detail
    @GetMapping("/bookDetail")
    public String bookDetail(@RequestParam("bookId") int theId, Model theModel) {
        //get the book from the service
        Book theBook = homePageService.getBook(theId);

        //get the author from the service
        List<Author> theAuthors = homePageService.getAllAuthors();

        //get all categories from the service
        List<Category> theCategories = homePageService.getAllCategories();

        //get top 3 books from the service
        List<Book> top3Books = homePageService.getTop3Books();

        // add to the spring model
        theModel.addAttribute("book", theBook);
        theModel.addAttribute("authors", theAuthors);
        theModel.addAttribute("categories", theCategories);
        theModel.addAttribute("top3Books", top3Books);

        return "Customer/HomePage/BookDetail";
    }

    // Get books by category
    @GetMapping("/category")
    public String getBooksByCategory(@RequestParam("categoryId") int categoryId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model theModel) {
        Page<Book> booksPage = homePageService.findBooksByCategory(categoryId, page, size);

        // Get all categories from the service
        List<Category> theCategories = homePageService.getAllCategories();

        //get the author from the service
        List<Author> theAuthors = homePageService.getAllAuthors();

        //get top 3 books from the service
        List<Book> top3Books = homePageService.getTop3Books();

        // Add to the spring model
        theModel.addAttribute("categories", theCategories);
        theModel.addAttribute("authors", theAuthors);
        theModel.addAttribute("booksPage", booksPage);
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", booksPage.getTotalPages());
        theModel.addAttribute("top3Books", top3Books);

        return "Customer/HomePage/HomePage";
    }

    //Get book by author
    @GetMapping("/authorDetail")
    public String authorDetail(@RequestParam("authorId") int authorId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model theModel) {
        // Get the author from the service
        Author theAuthor = homePageService.getAuthor(authorId);

        // Get books by the author from the service
        Page<Book> booksByAuthor = homePageService.getBooksByAuthor(authorId, page, size);

        // Get all categories from the service
        List<Category> theCategories = homePageService.getAllCategories();

        //get the author from the service
        List<Author> theAuthors = homePageService.getAllAuthors();

        //get top 3 books from the service
        List<Book> top3Books = homePageService.getTop3Books();

        // Add to the spring model
        theModel.addAttribute("categories", theCategories);
        theModel.addAttribute("authors", theAuthors);
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", booksByAuthor.getTotalPages());
        theModel.addAttribute("author", theAuthor);
        theModel.addAttribute("booksByAuthor", booksByAuthor);
        theModel.addAttribute("top3Books", top3Books);

        return "Customer/HomePage/AuthorInformation";
    }
}
