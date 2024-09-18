package SWP391_G2.com.example.library_Management.Staff.Admin.service;
import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCategoryService {
    @Autowired
    private AdminCategoryRepository adminCategoryRepository;
}
