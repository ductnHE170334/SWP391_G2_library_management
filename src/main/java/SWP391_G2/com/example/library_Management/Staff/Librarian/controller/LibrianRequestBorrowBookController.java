package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianBorrowIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/librarian/requestBorrow")
public class LibrianRequestBorrowBookController {
    @Autowired
    private LibrarianBorrowIndexService librarianBorrowIndexService;
    @GetMapping
    public String listBorrowIndex(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "4") int size,
                                  Model theModel) {
        // Lấy trang dữ liệu từ service
        Page<Borrow_index> borrowIndicesPage = librarianBorrowIndexService.getAllBorrowIndexPaginated(page, size);

        // Duyệt qua danh sách borrowIndices và in ra tên customer
        for (Borrow_index borrowIndex : borrowIndicesPage.getContent()) {
            if (borrowIndex.getCustomer() != null) {
                System.out.println("Customer Name: " + borrowIndex.getCustomer().getFirstName());
            } else {
                System.out.println("Customer Name: Not available");
            }
        }

        // Thêm dữ liệu vào model để truyền tới view
        theModel.addAttribute("borrowIndices", borrowIndicesPage.getContent());
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", borrowIndicesPage.getTotalPages());
        theModel.addAttribute("totalItems", borrowIndicesPage.getTotalElements());

        return "Staff/dashboard/Borrow_request/borrowIndexList"; // Trả về view tương ứng
    }

}
