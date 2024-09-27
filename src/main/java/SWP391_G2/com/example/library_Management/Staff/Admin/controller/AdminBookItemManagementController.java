package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminBookItemService;
import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/bookitems")
public class AdminBookItemManagementController {
    @Autowired
    private AdminBookItemService adminBookItemService;
    @Autowired
    private AdminBookService adminBookService;

    @GetMapping("/{id}")
    public String listBookItems(@PathVariable("id") int id, Model model){
        List<Book_item> bookItems = adminBookItemService.getAllBookItemsByBookId(id);
        Book book = adminBookService.getBookById(id);
        model.addAttribute("bookItems",bookItems);
        model.addAttribute("book",book);
        return "Staff/dashboard/Book/ManageBook_item";
    }
    @GetMapping("/addBookItem/{id}")
    public String viewAddBookItem(@PathVariable("id") int id, Model model){
        Book_item bookItem = new Book_item();
        Book book = adminBookService.getBookById(id);
        model.addAttribute("bookItem",bookItem);
        model.addAttribute("book",book);
        return "Staff/dashboard/Book/AddBook_item";
    }
    @GetMapping("/editBookItem/{id}")
    public String viewEditBookItem(@PathVariable("id") int id, Model model){
        Book_item bookItem = adminBookItemService.getBookItemById(id);
        System.out.println("Book name"+bookItem.getBook().getName());
        model.addAttribute("bookItem",bookItem);
        return "Staff/dashboard/Book/EditBook_item";
    }
    @GetMapping("/deleteBookItem/{bookItemId}")
    public String deleteBookItem(@PathVariable("bookItemId") int bookItemId, RedirectAttributes redirectAttributes) {

        Book_item bookItem = adminBookItemService.getBookItemById(bookItemId);

        // Get the associated Book follow by book item
        Book book = bookItem.getBook();

        // Decrease the quantity of the book by 1
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
        }


        adminBookService.save(book);


        adminBookItemService.deleteBookItem(bookItemId);


        redirectAttributes.addAttribute("id", book.getId());


        return "redirect:/bookitems/{id}";
    }



    @PostMapping("/save")
    public String saveOrUpdateBookItem(@ModelAttribute("bookItem") Book_item bookItem, @RequestParam("bookId") Integer bookId, RedirectAttributes redirectAttributes) {

        Book book = adminBookService.getBookById(bookId);
        System.out.println(bookItem.getId());


        bookItem.setBook(book);

        // bookItem.id = 0 => new book because in database no id equal 0
        if (bookItem.getId() == 0) {
            book.setQuantity(book.getQuantity() + 1);
            adminBookItemService.save(bookItem);
        } else {

            adminBookItemService.update(bookItem);
        }


        redirectAttributes.addAttribute("id", book.getId());
        return "redirect:/bookitems/" + book.getId();
    }


}
