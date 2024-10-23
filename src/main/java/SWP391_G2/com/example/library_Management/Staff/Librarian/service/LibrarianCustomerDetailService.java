package SWP391_G2.com.example.library_Management.Staff.Librarian.service;

import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBookItemRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianBorrowIndexRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class LibrarianCustomerDetailService {
    @Autowired
    LibrarianCustomerRepository librarianCustomerRepository;
    @Autowired
    LibrarianBookItemRepository librarianBookItemRepository;
    @Autowired
    LibrarianBorrowIndexRepository librarianBorrowIndexRepository;
    public Customer findCustomerById(int id) {
        Optional<Customer> result = librarianCustomerRepository.findById(Long.valueOf(id));
        return result.orElse(null);
    }



    public List<Borrow_index> getListBorrowindexByCustomer(int id) {
        Customer customer = findCustomerById(id);
        return librarianBorrowIndexRepository.findByCustomer(customer);
    }
}
