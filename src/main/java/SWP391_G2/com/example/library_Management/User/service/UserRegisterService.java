package SWP391_G2.com.example.library_Management.User.service;

import SWP391_G2.com.example.library_Management.Customer.repository.UserRepository;
import SWP391_G2.com.example.library_Management.Entity.Role;
import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public boolean registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public Role findRoleById(int id) {
        return roleRepository.findById(id).orElse(null); // Retrieve role or return null if not found
    }
}
