package SWP391_G2.com.example.library_Management.User.controller;

import SWP391_G2.com.example.library_Management.Entity.User;
import SWP391_G2.com.example.library_Management.User.dto.request.UserLoginRequest;
import SWP391_G2.com.example.library_Management.User.dto.request.UserRegisterRequest;
import SWP391_G2.com.example.library_Management.User.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegisterRequest()); // Đổi registerUser thành user
        return "Customer/register"; // Trả về trang đăng ký
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegisterRequest request, Model model) { // Đổi tên phương thức thành registerUser
        userService.register(request);
        model.addAttribute("message", "Registration successful!");
        return "redirect:/user/login"; // Đổi đường dẫn customer thành user
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginUser", new UserLoginRequest()); // Thêm đối tượng loginUser
        return "Customer/login"; // Trả về trang đăng nhập
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("loginUser") UserLoginRequest request, Model model, HttpSession session) {
        User user = userService.login(request);

        if (user != null) {
            session.setAttribute("loggedInUser", user); // Lưu thông tin người dùng vào session
            String roleName = user.getRole().getName();

            // Điều hướng dựa vào vai trò người dùng
            switch (roleName) {
                case "Admin":
                    return "redirect:/admin/home";
                case "Librarian":
                    return "redirect:/librarian/home";
                case "Customer":
                    return "redirect:/user/homepage";
                case "Content writer":
                    return "redirect:/news/newpage";
                default:
                    model.addAttribute("error", "Invalid role!");
                    return "Customer/login";
            }
        } else {
            model.addAttribute("error", "Invalid email or password!"); // Đăng nhập thất bại
            return "Customer/login";
        }
    }



    @GetMapping("/profile")
    public String showUserProfile(Model model, HttpSession session) {
        // Giả sử bạn đang lưu thông tin người dùng trong session với key là "loggedInUser"
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null) {
            model.addAttribute("user", user); // Đặt đối tượng user vào mô hình
        } else {
            model.addAttribute("error", "User not found!"); // Xử lý khi không tìm thấy người dùng
        }

        return "Customer/profile"; // Trả về view
    }
    @GetMapping("/profile/edit")
    public String showEditProfile(Model model, HttpSession session) {
        // Lấy thông tin người dùng từ session
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("user", user);
            return "Customer/editProfile"; // Trả về trang chỉnh sửa thông tin
        } else {
            model.addAttribute("error", "User not found!");
            return "Customer/error"; // Trả về trang lỗi nếu không tìm thấy user
        }
    }
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("user") UserRegisterRequest request, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            // Cập nhật thông tin người dùng
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            userService.updateUser(user); // Cập nhật thông tin trong cơ sở dữ liệu

            session.setAttribute("loggedInUser", user); // Cập nhật thông tin trong session
            model.addAttribute("message", "Profile updated successfully!");
            return "redirect:/user/profile"; // Quay về trang profile
        } else {
            model.addAttribute("error", "User not found!");
            return "Customer/error"; // Trả về trang lỗi nếu không tìm thấy user
        }
    }
    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model, HttpSession session) {
        // Lấy thông tin người dùng từ session
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            return "Customer/changePassword"; // Trả về trang đổi mật khẩu
        } else {
            model.addAttribute("error", "User not found!");
            return "Customer/error"; // Trả về trang lỗi nếu không tìm thấy user
        }
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model,
            HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user); // Gán user vào mô hình để sử dụng trong template

        if (user != null) {
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("error", "New password and confirmation do not match!");
                return "Customer/profile";
            }
            int userId = user.getId();
            boolean isChanged = userService.changePassword(userId, oldPassword, newPassword);
            if (isChanged) {
                model.addAttribute("message", "Password changed successfully!");
            } else {
                model.addAttribute("error", "Old password is incorrect!");
            }
        } else {
            model.addAttribute("error", "User not found!");
        }
        return "Customer/profile";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa toàn bộ session
        return "redirect:/user/login"; // Điều hướng về trang đăng nhập
    }

    @GetMapping("/homepage")
    public String homePage(Model model) {
        return "Customer/HomePage/HomePage";  // Đảm bảo đường dẫn đến trang homepage
    }
    @GetMapping("/newpage")
    public String newpage(Model model ){
        return "Customer/News/NewPage";
    }
}
