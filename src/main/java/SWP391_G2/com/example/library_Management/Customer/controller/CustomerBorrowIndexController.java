package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerBorrowIndexService;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/borrow")
public class CustomerBorrowIndexController {
    @Autowired
    private CustomerBorrowIndexService customerBorrowIndexService;

    @GetMapping("/{userId}")
    public String getListTrackBorrowBook(@PathVariable Long userId, Model model) {
        List<Borrow_index> borrowIndexList = customerBorrowIndexService.getBorrowindexListByCustomerId(userId);

        System.out.println("=========Print borrow Index============");
        for (Borrow_index borrow : borrowIndexList) {
            System.out.println("Borrow ID: " + borrow.getId());
        }

        model.addAttribute("borrowIndexList", borrowIndexList);
        model.addAttribute("userId",userId);

        return "Customer/bookTracking";
    }
    @GetMapping("/delete/{customerId}/{bookItemId}")
    public String deleteBorrowIndex(@PathVariable Long userId, @PathVariable Long bookItemId, Model model) {
        System.out.println("Customer ID: " + userId);
        System.out.println("Book Item ID: " + bookItemId);

        customerBorrowIndexService.deleteBorrowIndex(userId, bookItemId);

        return "redirect:/borrow/" + userId;
    }

}
