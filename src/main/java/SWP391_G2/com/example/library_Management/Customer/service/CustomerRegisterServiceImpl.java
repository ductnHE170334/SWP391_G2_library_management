package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.dto.request.CustomerRegisterRequest;
import SWP391_G2.com.example.library_Management.Customer.repository.CustomerRepository;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;  // Đảm bảo import đúng PasswordEncoder
import org.springframework.stereotype.Service;

@Service
public class CustomerRegisterService {

    @Autowired
    @Enable
    private CustomerRepository customerRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;  // Đưa PasswordEncoder ra cấp lớp

    public Customer registerNewCustomer(CustomerRegisterRequest registerRequest) {
        // Kiểm tra xem email có tồn tại hay không
        if (customerRepository.findByEmail(registerRequest.getEmail()) != null) {
            throw new IllegalStateException("Email is already taken");
        }

        // Tạo đối tượng Customer mới và gán các thuộc tính
        Customer customer = new Customer();
        customer.setFirstName(registerRequest.getFirstName());
        customer.setLastName(registerRequest.getLastName());
        customer.setEmail(registerRequest.getEmail());

        // Mã hóa mật khẩu trước khi lưu
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        customer.setPassword(encodedPassword);

        customer.setPhone(registerRequest.getPhone());

        // Lưu đối tượng Customer vào cơ sở dữ liệu
        return customerRepository.save(customer);
    }
}
