package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.CustomerRepository;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService  {

    @Autowired
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

//    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
//        this.customerRepository = customerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public Customer registerCustomer(Customer customer) {
        // Encode the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    // Phương thức đăng nhập
    public boolean login(String email, String rawPassword) {
        // Tìm kiếm khách hàng theo email
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));

        // Kiểm tra mật khẩu: so sánh mật khẩu thô với mật khẩu đã mã hóa
        return passwordEncoder.matches(rawPassword, customer.getPassword());
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
    }
}