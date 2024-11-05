package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.CustomerApplicationTypeRepository;
import SWP391_G2.com.example.library_Management.Entity.Application_Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerApplicationTypeService {
    @Autowired
    CustomerApplicationTypeRepository customerApplicationTypeRepository;

    public Application_Type getApplicationTypeById(int applicationTypeId) {
        return customerApplicationTypeRepository.findById(applicationTypeId).get();

        // Return the found Application_Type or throw an exception if not found

    }
}
