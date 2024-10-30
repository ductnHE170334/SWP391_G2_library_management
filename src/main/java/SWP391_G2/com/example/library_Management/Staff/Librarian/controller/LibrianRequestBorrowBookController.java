package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianBorrowIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        // Chỉ lấy các Borrow_index có status_id = 1
        Page<Borrow_index> borrowIndicesPage = librarianBorrowIndexService.getBorrowIndexByStatusIdPaginated(page, size);

        // Thêm dữ liệu vào model để truyền tới view
        theModel.addAttribute("borrowIndices", borrowIndicesPage.getContent());
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", borrowIndicesPage.getTotalPages());
        theModel.addAttribute("totalItems", borrowIndicesPage.getTotalElements());

        return "Staff/dashboard/Borrow_request/borrowIndexList"; // Trả về view tương ứng
    }
    @GetMapping("/accept/{id}")
    public String acceptBorrowRequest(@PathVariable("id") int id) {
        librarianBorrowIndexService.updateBorrowStatus(id, 2); // status_id = 2
        return "redirect:/librarian/requestBorrow"; // Chuyển hướng về trang danh sách
    }

    @GetMapping("/deny/{id}")
    public String denyBorrowRequest(@PathVariable("id") int id) {
        librarianBorrowIndexService.updateBorrowStatus(id, 4); // status_id = 4
        return "redirect:/librarian/requestBorrow"; // Chuyển hướng về trang danh sách
    }

}
