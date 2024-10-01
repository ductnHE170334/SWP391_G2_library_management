package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.dto.request.CustomerLoginRequest;
import SWP391_G2.com.example.library_Management.Customer.login.response.LoginMessage;
import SWP391_G2.com.example.library_Management.Customer.service.CustomerLoginServiceImpl;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer/login")
public class CustomerLoginController {
//    @Autowired
//    private CustomerLoginServiceImpl customerLoginServiceImpl;
//
//    @GetMapping("/login")
//    public String showCustomerLoginForm(Model model) {
//        return "Customer/login_register"; // Trỏ tới file login_register.html của khách hàng
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
//        Customer customer = customerLoginServiceImpl.authenticate(email, password);
//        if (customer != null) {
//            session.setAttribute("customer", customer);
//            return "redirect:/home";  // Chuyển hướng đến trang home của khách hàng
//        }
//        return "redirect:/customer/login?error=true"; // Nếu đăng nhập thất bại, chuyển hướng lại trang đăng nhập
//    }
//
//    @GetMapping("/dashboard")
//    public String showCustomerDashboard() {
//        return "Customer/fragments/header"; // Trỏ đến header của khách hàng hoặc trang dashboard nếu có
//    }

    @Autowired
    private CustomerLoginServiceImpl customerLoginService;

    @PostMapping
    public ResponseEntity<LoginMessage> loginCustomer(@RequestBody CustomerLoginRequest customerLoginRequest) {
        LoginMessage loginMessage = customerLoginService.loginCustomer(customerLoginRequest);  // Gọi đến EmployeeLoginService
        return ResponseEntity.ok(loginMessage); // Trả về kết quả đăng nhập
    }
}
