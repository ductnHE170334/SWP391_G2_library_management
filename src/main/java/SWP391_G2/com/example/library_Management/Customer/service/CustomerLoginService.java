package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginService {
    @Autowired
    private UserRepository userRepository;
}
