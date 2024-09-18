package SWP391_G2.com.example.library_Management.Staff.StaffCommon.service;

import SWP391_G2.com.example.library_Management.Staff.StaffCommon.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffLoginService {
    @Autowired
    private StaffRepository staffRepository;
}
