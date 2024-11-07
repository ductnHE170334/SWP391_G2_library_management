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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
    public String addOrUpdateBook(@ModelAttribute("book") Book book,
                                  @RequestParam("image") MultipartFile image,
                                  Model model) {
        // Check if it's an addition or update
        if (book.getId() == 0) { // Adding a new book
            if (adminBookService.existsByName(book.getName())) {
                // If book name exists, return form with error message
                List<Category> categories = adminBookService.getAllCategories();
                List<Author> authors = adminBookService.getAllAuthors();
                List<Publisher> publishers = adminBookService.getAllPublisher();
                model.addAttribute("categories", categories);
                model.addAttribute("authors", authors);
                model.addAttribute("publishers", publishers);
                model.addAttribute("book", new Book());
                model.addAttribute("errorMessage", "A book with the same name already exists.");
                return "Staff/dashboard/Book/EditBook";
            }
        }

        // Handle image upload if not empty
        if (!image.isEmpty()) {
            try {
                // Get the original file name and create a unique file name
                String originalFilename = image.getOriginalFilename();
                String newFilename = System.currentTimeMillis() + "_" + originalFilename;

                // Define the directory to store images (absolute path)
                String uploadDir = "src/main/resources/static/uploads/";
                Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

                // Create directory if it doesn't exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save image file
                Path filePath = uploadPath.resolve(newFilename);
                image.transferTo(filePath.toFile());

                // Update the `image_url` attribute of the book
                book.setImage_url(newFilename);

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Error saving image file.");
                return "Staff/dashboard/Book/EditBook";
            }
        } else if (book.getId() != 0) {
            // On update, retain existing image if no new image is chosen
            Optional<Book> existingBook = adminBookService.findById(book.getId());
            existingBook.ifPresent(value -> book.setImage_url(value.getImage_url()));
        }

        // Save or update the book in the database
        if (book.getId() != 0) {
            adminBookService.update(book); // Update existing book
        } else {
            adminBookService.save(book); // Save new book
        }

        return "redirect:/books";
    }


}
