//package SWP391_G2.com.example.library_Management.Staff.Admin.service;
//
//import SWP391_G2.com.example.library_Management.Entity.Book_item;
//import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminBookItemRepository;
//
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminBookItemService {
//    @Autowired
//    private AdminBookItemRepository adminBookItemRepository;
//
//    public List<Book_item> getAllBookItemsByBookId(int id) {
//        return adminBookItemRepository.findByBookId(id);
//    }
//
//    public void save(Book_item bookItem) {
//        adminBookItemRepository.save(bookItem);
//    }
//
//    @Transactional
//    public Book_item getBookItemById(Integer id) {
//        return adminBookItemRepository.findById(String.valueOf(id)).get();
//    }
//
//    public void update(Book_item bookItem) {
//        if (adminBookItemRepository.existsById(String.valueOf(bookItem.getId()))) {
//            adminBookItemRepository.save(bookItem);
//        } else {
//            throw new RuntimeException("BookItem not found for update");
//        }
//    }
//
//    public void deleteBookItem(Integer id) {
//        // Kiểm tra xem bookItem có tồn tại hay không
//        if (adminBookItemRepository.existsById(String.valueOf(id))) {
//            adminBookItemRepository.deleteById(String.valueOf(id));
//        } else {
//            throw new RuntimeException("BookItem with id " + id + " does not exist.");
//        }
//    }
//}
