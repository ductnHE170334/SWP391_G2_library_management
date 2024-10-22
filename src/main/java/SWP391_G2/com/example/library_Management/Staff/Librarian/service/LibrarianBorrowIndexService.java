package SWP391_G2.com.example.library_Management.Staff.Librarian.service;

import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.Status;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBookItemRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBorrowIndexRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LibrarianBorrowIndexService {
    @Autowired
    private LibrarianBorrowIndexRepository librarianBorrowIndexRepository;
    @Autowired
    private LibrarianStatusRepository librarianStatusRepository;
    @Autowired
    private LibrarianBookItemRepository librarianBookItemRepository;

    public Page<Borrow_index> getAllBorrowIndexPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // `page - 1` vì Spring Data JPA bắt đầu từ 0
        return librarianBorrowIndexRepository.findAll(pageable);
    }


    public void updateBorrowStatus(int id, int statusId) {
        // Lấy Borrow_index dựa trên id
        Borrow_index optionalBorrowIndex = librarianBorrowIndexRepository.findById(Long.valueOf(id)).get();

        if (optionalBorrowIndex != null) {


            // Cập nhật trạng thái
            Status status = librarianStatusRepository.findById(String.valueOf(statusId)).get();
            optionalBorrowIndex.setStatus_id(status);

            // Nếu trạng thái là Accepted (statusId == 2), cập nhật borrow_date và return_date
            if (statusId == 2) {
                LocalDateTime now = LocalDateTime.now();
                optionalBorrowIndex.setBorrow_date(now);
                optionalBorrowIndex.setReturn_date(now.plusDays(7));
                Book_item bookItem = optionalBorrowIndex.getBook_item();
                if (bookItem != null) {
                    bookItem.setCondition("Unavailable");
                    librarianBookItemRepository.save(bookItem);
                }
            }

            // Lưu lại thông tin cập nhật
            librarianBorrowIndexRepository.save(optionalBorrowIndex);
        } else {
            throw new RuntimeException("Borrow Index không tồn tại với id: " + id);
        }
    }
}
