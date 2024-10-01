package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.dto.request.CustomerRegisterRequest;
import SWP391_G2.com.example.library_Management.Customer.service.CustomerRegisterServiceImpl;
import SWP391_G2.com.example.library_Management.Customer.service.CustomerService;
import SWP391_G2.com.example.library_Management.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("customer/register")
public class CustomerRegisterController {

    @Autowired
    private CustomerRegisterServiceImpl customerRegisterService;

    // Phương thức GET để hiển thị form đăng ký
    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer/login_register"; // Trả về trang đăng ký
    }

    // Phương thức POST để xử lý đăng ký
    @PostMapping(path = "/save")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerRegisterRequest customerRegisterRequest) {
        String email = customerRegisterService.addCustomer(customerRegisterRequest); // Gọi đến CustomerRegisterService
        return ResponseEntity.ok(email); // Trả về email sau khi đăng ký thành công
    }
}

