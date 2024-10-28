//package SWP391_G2.com.example.library_Management.Staff.Librarian.controller;
//
//import SWP391_G2.com.example.library_Management.Entity.Author;
//import SWP391_G2.com.example.library_Management.Entity.Book;
//import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBookRepository;
//import SWP391_G2.com.example.library_Management.Staff.Librarian.service.LibrarianBookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/mybooks")
//public class LibrarianBookController {
//
//
//    @Autowired
//    LibrarianBookRepository repository;
//
//    @GetMapping({"", "/"})
//    public String getAllBooks(Model model,
//                                @RequestParam(defaultValue = "1") int page,
//                                @RequestParam(defaultValue = "5") int size,
//                                @RequestParam(required = false) String search) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Book> bookPage;
//
//        if (search != null && !search.isEmpty()) {
//            bookPage = repository.findByNameContainingIgnoreCase(search, pageable);
//        } else {
//            bookPage = repository.findAll(pageable);
//        }
//
//        model.addAttribute("bookPage", bookPage);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", bookPage.getTotalPages());
//        model.addAttribute("search", search);  // Make sure this is included
//        return "Staff/dashboard/Book/list";  // Ensure this path is correct
//    }
//}
