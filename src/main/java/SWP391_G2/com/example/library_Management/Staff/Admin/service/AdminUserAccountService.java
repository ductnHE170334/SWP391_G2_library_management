package SWP391_G2.com.example.library_Management.Staff.Admin.service;

import SWP391_G2.com.example.library_Management.Entity.Role;
import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminUserAccountRepository;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserAccountService {

    private final AdminUserAccountRepository adminUserAccountRepository;
    private final RoleRepository roleRepository;

    // Constructor injection
    @Autowired
    public AdminUserAccountService(AdminUserAccountRepository adminUserAccountRepository,
                                   RoleRepository roleRepository) {
        this.adminUserAccountRepository = adminUserAccountRepository;
        this.roleRepository = roleRepository;
    }

    // Get all user with pagination
    public Page<User> getUser(Pageable pageable) {
        return adminUserAccountRepository.findAll(pageable);
    }

    // Get a specific user by ID
    public User getUser(String id) {
        return adminUserAccountRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Add new User
    public User addUser(User user) {
        return adminUserAccountRepository.save(user);
    }

    // Update a user
    public User updateUser(int id, User updatedUser) {
        return adminUserAccountRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setPassword(updatedUser.getPassword());
                    user.setRole(updatedUser.getRole());
                    return adminUserAccountRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Delete user by ID
    public void deleteUser(int id) {
        adminUserAccountRepository.deleteById(id);
    }

    // Search for user by name with pagination
    public Page<User> searchUserByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return adminUserAccountRepository.findByLastNameContaining(name, pageable);
    }

    // Get role by user id
    public Role getRoleByUserId(int userId) {
        User user = adminUserAccountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return user.getRole();
    }
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
