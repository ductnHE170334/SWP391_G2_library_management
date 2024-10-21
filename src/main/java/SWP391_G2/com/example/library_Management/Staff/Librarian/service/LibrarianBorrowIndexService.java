package SWP391_G2.com.example.library_Management.Staff.Librarian.service;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBorrowIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibrarianBorrowIndexService {
    @Autowired
    private LibrarianBorrowIndexRepository librarianBorrowIndexRepository;

    public Page<Borrow_index> getAllBorrowIndexPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // `page - 1` vì Spring Data JPA bắt đầu từ 0
        return librarianBorrowIndexRepository.findAll(pageable);
    }


}
