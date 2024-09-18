package SWP391_G2.com.example.library_Management.Staff.StaffCommon.controller;

import SWP391_G2.com.example.library_Management.Staff.StaffCommon.service.StaffLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff/login")
public class StaffLoginController {
    @Autowired
    private StaffLoginService staffLoginService;
}
