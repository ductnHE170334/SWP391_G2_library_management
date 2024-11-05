package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.CustomerApplicationRepository;
import SWP391_G2.com.example.library_Management.Customer.repository.CustomerApplicationTypeRepository;
import SWP391_G2.com.example.library_Management.Entity.Application;
import SWP391_G2.com.example.library_Management.Entity.Application_Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerApplicationService {
    @Autowired
    CustomerApplicationRepository customerApplicationRepository;
    @Autowired
    private CustomerApplicationTypeRepository customerApplicationTypeRepository;
    public List<Application> getApplicationsByUserId(Long userId) {
        return customerApplicationRepository.findByUser_Id(userId);
    }


    public List<Application_Type> getAllApplicationTypes() {
        return customerApplicationTypeRepository.findAll();
    }

    public Application saveApplication(Application application) {
        if (application.getStatus() == null) {
            application.setStatus("Waiting");
        }

        return customerApplicationRepository.save(application);
    }
}
