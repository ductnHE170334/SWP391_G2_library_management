package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.CustomerBookItemRepository;
import SWP391_G2.com.example.library_Management.Customer.repository.CustomerBorrowIndexRepository;
import SWP391_G2.com.example.library_Management.Customer.repository.CustomerRepository;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import SWP391_G2.com.example.library_Management.Entity.Borrow_index;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBorrowIndexService {
    @Autowired
    private CustomerBorrowIndexRepository customerBorrowIndexRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerBookItemRepository customerBookItemRepository;

    public List<Borrow_index> getBorrowindexListByCustomerId(Long customerId) {

        Customer customer = customerRepository.findById(String.valueOf(customerId)).orElseThrow(() -> new RuntimeException("Customer not found"));

        return customerBorrowIndexRepository.findByCustomer(customer);
    }

    public void deleteBorrowIndex(Long customerId, Long bookItemId) {
        Book_item bookItem = customerBookItemRepository.findById(String.valueOf(bookItemId)).get();
        Customer customer = customerRepository.findById(String.valueOf(customerId)).orElseThrow(() -> new RuntimeException("Customer not found"));
        Borrow_index borrowIndex = customerBorrowIndexRepository.findByCustomerAndBook_item(customer, bookItem);

        if (borrowIndex != null) {
            customerBorrowIndexRepository.delete(borrowIndex);
        }
    }

}

