package SWP391_G2.com.example.library_Management.User.service;

import SWP391_G2.com.example.library_Management.Customer.repository.UserRepository;
import SWP391_G2.com.example.library_Management.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    @Autowired
    private UserRepository userRepository;


    // Lấy người dùng theo ID
    public User getUser(String id) {
        return userRepository.findById(String.valueOf(Integer.valueOf(id))).orElse(null); // Trả về người dùng hoặc null nếu không tìm thấy
    }

    // Cập nhật thông tin người dùng
    public User updateUser(int id, User updatedUser) {
        return userRepository.findById(String.valueOf(Integer.valueOf(id)))
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setPassword(updatedUser.getPassword());
                    user.setRole(updatedUser.getRole());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id)); // Thông báo lỗi nếu không tìm thấy người dùng
    }
}
