package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminCustomerDetailRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBookItemRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBorrowIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AdminCustomerDetailService {
    @Autowired
    AdminCustomerDetailRepository adminCustomerDetailRepository;
    @Autowired
    LibrarianBookItemRepository librarianBookItemRepository;
    @Autowired
    LibrarianBorrowIndexRepository librarianBorrowIndexRepository;
    public User findCustomerById(int id) {
        Optional<User> result = adminCustomerDetailRepository.findById(Long.valueOf(id));
        return result.orElse(null);
    }



    public List<Borrow_index> getListBorrowindexByCustomer(int id) {
        User user = findCustomerById(id);
        return librarianBorrowIndexRepository.findByCustomer(user);
    }
}