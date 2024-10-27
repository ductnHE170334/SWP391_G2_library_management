package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianBorrowIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/librarian/trackBorrowBook")
public class LibrarianTrackBorrowBookController {
    @Autowired
    private LibrarianBorrowIndexService librarianBorrowIndexService;
    @GetMapping
    public String listBorrowIndexExcludingStatusOne(@RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "size", defaultValue = "4") int size,
                                                    Model theModel) {
        // Lấy các Borrow_index có status.id khác 1
        Page<Borrow_index> borrowIndicesPage = librarianBorrowIndexService.getBorrowIndexByStatusIdNotPaginated(page, size);

        // Thêm dữ liệu vào model để truyền tới view
        theModel.addAttribute("borrowIndices", borrowIndicesPage.getContent());
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", borrowIndicesPage.getTotalPages());
        theModel.addAttribute("totalItems", borrowIndicesPage.getTotalElements());

        return "Staff/dashboard/Borrow_request/borrowTracking"; // Trả về view tương ứng
    }
    @PostMapping("/extendDueDate")
    public String extendDueDate(@RequestParam("borrowIndexId") Long borrowIndexId,
                                @RequestParam("extensionWeeks") int extensionWeeks,
                                Model theModel) {
        // Lấy Borrow_index theo ID
        Borrow_index borrowIndex = librarianBorrowIndexService.findById(borrowIndexId);
        if (borrowIndex != null) {
            // Tính toán ngày trả mới
            LocalDateTime currentReturnDate = borrowIndex.getReturnDate();
            LocalDateTime newReturnDate = currentReturnDate.plus(extensionWeeks, ChronoUnit.WEEKS);
            borrowIndex.setReturnDate(newReturnDate); // Cập nhật lại ngày trả

            // Lưu Borrow_index đã cập nhật
            librarianBorrowIndexService.save(borrowIndex);
        }

        // Sau khi cập nhật, chuyển hướng về trang danh sách mượn
        return "redirect:/librarian/trackBorrowBook"; // Redirect lại về trang danh sách
    }
}
