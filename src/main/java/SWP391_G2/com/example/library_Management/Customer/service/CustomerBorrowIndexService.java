package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.CustomerBorrowIndexRepository;
import SWP391_G2.com.example.library_Management.Customer.repository.CustomerRepository;
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

    public List<Borrow_index> getBorrowindexListByCustomerId(Long customerId) {
        // Tìm kiếm khách hàng từ customerId
        Customer customer = customerRepository.findById(String.valueOf(customerId)).orElseThrow(() -> new RuntimeException("Customer not found"));
        // Truy vấn danh sách Borrow_index từ customer
        return customerBorrowIndexRepository.findByCustomer(customer);
    }
}

