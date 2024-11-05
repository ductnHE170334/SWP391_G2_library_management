package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.CustomerBookItemRepository;
import SWP391_G2.com.example.library_Management.Customer.repository.CustomerBorrowIndexRepository;
import SWP391_G2.com.example.library_Management.Customer.repository.UserRepository;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBorrowIndexService {
    @Autowired
    private CustomerBorrowIndexRepository customerBorrowIndexRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerBookItemRepository customerBookItemRepository;

    public List<Borrow_index> getBorrowindexListByCustomerId(Long userId) {

        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new RuntimeException("Customer not found"));

        return customerBorrowIndexRepository.findByCustomer(user);
    }

    public void deleteBorrowIndex(Long userId, Long bookItemId) {
        Book_item bookItem = customerBookItemRepository.findById(String.valueOf(bookItemId)).get();
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new RuntimeException("Customer not found"));
        Borrow_index borrowIndex = customerBorrowIndexRepository.findByCustomerAndBookItem(user, bookItem);

        if (borrowIndex != null) {
            customerBorrowIndexRepository.delete(borrowIndex);
        }
    }
    public Borrow_index getBorrowIndexById(Long borrowId) {
        return customerBorrowIndexRepository.findById(Math.toIntExact(borrowId))
                .orElseThrow(() -> new RuntimeException("Borrow index not found"));
    }

}

