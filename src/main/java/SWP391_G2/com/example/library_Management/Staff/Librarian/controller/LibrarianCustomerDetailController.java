package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianCustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/librarian/customerDetail")
public class LibrarianCustomerDetailController {
    @Autowired
    LibrarianCustomerDetailService librarianCustomerDetailService;
    @GetMapping("/{id}")
    public String showCustomerDetail(@PathVariable("id") int id, Model model) {
        Customer customer = librarianCustomerDetailService.findCustomerById(id);
        List<Borrow_index> borrowIndexList = librarianCustomerDetailService.getListBorrowindexByCustomer(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("borrowIndexList",borrowIndexList);
            return "Staff/dashboard/Borrow_request/CustomerDetail";
        } else {
            return "redirect:/librarian/requestBorrow";
        }
    }
}
