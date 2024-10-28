package SWP391_G2.com.example.library_Management.User.service;

import SWP391_G2.com.example.library_Management.Entity.Role;
import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.User.dto.request.UserLoginRequest;
import SWP391_G2.com.example.library_Management.User.dto.request.UserRegisterRequest;
import SWP391_G2.com.example.library_Management.User.repository.RoleRepository;
import SWP391_G2.com.example.library_Management.User.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User register(UserRegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Không mã hóa mật khẩu
        user.setPhone(request.getPhone());
        // Gán role 'Customer' cho người dùng
        Role role = roleRepository.findByName("Customer");
        user.setRole(role);
        return usersRepository.save(user);
    }

    public User login(UserLoginRequest request) {
        User user = usersRepository.findByEmail(request.getEmail()).orElse(null);
        if (user != null && user.getPassword().equals(request.getPassword())) { // Kiểm tra email và mật khẩu
            return user; // Trả về đối tượng User
        }
        return null; // Trả về null nếu không tìm thấy user
    }
    public User updateUser(User user) {
        return usersRepository.save(user); // Cập nhật thông tin trong cơ sở dữ liệu
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        User user = usersRepository.findById(userId).orElse(null); // Tìm user theo ID
        if (user != null) {
            // Kiểm tra mật khẩu cũ (nếu không mã hóa)
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword); // Cập nhật mật khẩu mới
                usersRepository.save(user); // Lưu lại thay đổi
                return true; // Trả về true nếu thay đổi thành công
            }
        }
        return false; // Trả về false nếu không thành công
    }

}
