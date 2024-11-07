package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.UserRepository;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserService {
    @Autowired
    UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(String.valueOf(userId)).get();
    }
}
