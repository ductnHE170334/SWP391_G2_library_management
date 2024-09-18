package SWP391_G2.com.example.library_Management.Staff.Admin.controller;

import SWP391_G2.com.example.library_Management.Staff.Admin.service.AdminBookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookitems")
public class AdminBookItemManagementController {
    @Autowired
    private AdminBookItemService adminBookItemService;
}
