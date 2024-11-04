package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerBorrowIndexService;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping
    public String getListTrackBorrowBook(HttpSession session, Model model) {
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;

        // Kiểm tra kiểu dữ liệu của userId trong session
        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

        if (userId == null) {
            // Xử lý trường hợp userId không được thiết lập trong session
            model.addAttribute("message", "User not logged in.");
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập hoặc trang phù hợp
        }

        List<Borrow_index> borrowIndexList = customerBorrowIndexService.getBorrowindexListByCustomerId(userId);

        System.out.println("=========Print borrow Index============");
        for (Borrow_index borrow : borrowIndexList) {
            System.out.println("Borrow ID: " + borrow.getId());
        }

        model.addAttribute("borrowIndexList", borrowIndexList);
        model.addAttribute("userId", userId);

        return "Customer/RequestBorrow/bookTracking";
    }

    @GetMapping("/delete/{bookItemId}")
    public String deleteBorrowIndex(HttpSession session, @PathVariable Long bookItemId, Model model) {
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;

        // Kiểm tra kiểu dữ liệu của userId trong session
        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

        if (userId == null) {
            // Xử lý trường hợp userId không được thiết lập trong session
            model.addAttribute("message", "User not logged in.");
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập hoặc trang phù hợp
        }

        System.out.println("Customer ID: " + userId);
        System.out.println("Book Item ID: " + bookItemId);

        customerBorrowIndexService.deleteBorrowIndex(userId, bookItemId);

        return "redirect:/borrow";
    }
}
