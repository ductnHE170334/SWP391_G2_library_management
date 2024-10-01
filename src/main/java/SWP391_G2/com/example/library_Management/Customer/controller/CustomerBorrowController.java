package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.Enum.BorrowRequestStatus;
import SWP391_G2.com.example.library_Management.Customer.service.IBookService;
import SWP391_G2.com.example.library_Management.Customer.service.ICustomerRequestBorrow;
import SWP391_G2.com.example.library_Management.Entity.Book;
import SWP391_G2.com.example.library_Management.Entity.Borrow_history;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/borrow")
public class CustomerBorrowController {
    private final ICustomerRequestBorrow service;
    private final IBookService bookService;
    @GetMapping
    public String ListBorrowRequestCustomers(Model model,
                                             @RequestParam(value = "page",defaultValue = "1") int page,
                                             @RequestParam(value = "size",defaultValue = "5") int size) {
        Page<Borrow_history> list = service.getList(PageRequest.of(page - 1, size));
        List<Book> books = bookService.getBooksCanBorrow();
        model.addAttribute("list", list);
        model.addAttribute("books", books);
        return "Customer/RequestBorrow/ListBorrowRequst";
    }

    @PostMapping
    public String createBorrowRequest(@RequestParam("bdate") String bdate,
                                      @RequestParam("rdate") String rdate,
                                      @RequestParam("bookID") int bookID) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Borrow_history borrowHistory = new Borrow_history();
        borrowHistory.setBorrow_date(formatter.parse(bdate));
        borrowHistory.setReturn_date(formatter.parse(rdate));
        borrowHistory.setStatus(BorrowRequestStatus.PENDING);
        borrowHistory.setBook_item(bookService.getBookById(bookID));
        service.addBorrowRequest(borrowHistory);
        return "redirect:/customer/borrow";
    }

    @PostMapping("/{id}")
    public String updateBorrowRequest(@PathVariable("id") int id,@RequestParam("bdate") String bdate,
                                      @RequestParam("rdate") String rdate,
                                      @RequestParam("bookID") int bookID) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Borrow_history borrowHistory = service.getBorrowRequest(id);
        borrowHistory.setBorrow_date(formatter.parse(bdate));
        borrowHistory.setReturn_date(formatter.parse(rdate));
        borrowHistory.setBook_item(bookService.getBookById(bookID));
        service.addBorrowRequest(borrowHistory);
        return "redirect:/customer/borrow";
    }
}
