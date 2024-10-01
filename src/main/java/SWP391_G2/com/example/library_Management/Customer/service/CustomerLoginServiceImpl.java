package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.dto.request.CustomerLoginRequest;
import SWP391_G2.com.example.library_Management.Customer.login.response.LoginMessage;
import SWP391_G2.com.example.library_Management.Customer.repository.CustomerRepository;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public LoginMessage loginEmployee(CustomerLoginRequest customerLoginRequest) {
        Customer customer = customerRepository.findByEmail(customerLoginRequest.getEmail());
        if (customer != null) {
            String password = customerLoginRequest.getPassword();
            String encodedPassword = customer.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Customer> validCustomer = customerRepository.findOneByEmailAndPassword(customerLoginRequest.getEmail(), encodedPassword);
                if (validCustomer.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("Password Not Match", false);
            }
        } else {
            return new LoginMessage("Email Not Exists", false);
        }
    }

    @Override
    public LoginMessage loginCustomer(CustomerLoginRequest customerLoginRequest) {
        return null;
    }
}
