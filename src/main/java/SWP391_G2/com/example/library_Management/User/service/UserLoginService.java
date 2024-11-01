package SWP391_G2.com.example.library_Management.User.service;

import SWP391_G2.com.example.library_Management.Customer.repository.UserRepository;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;

    // Authenticate the user by email and password
    public User loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
