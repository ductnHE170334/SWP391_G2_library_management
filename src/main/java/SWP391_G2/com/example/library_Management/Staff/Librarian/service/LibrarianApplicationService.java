package SWP391_G2.com.example.library_Management.Staff.Librarian.service;

import SWP391_G2.com.example.library_Management.Entity.Application;
import SWP391_G2.com.example.library_Management.Entity.Application_Type;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianApplicationRepository;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianApplicationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibrarianApplicationService {
    @Autowired
    LibrarianApplicationRepository librarianApplicationRepository;
    @Autowired
    LibrarianApplicationTypeRepository librarianApplicationTypeRepository;
    public Page<Application> getApplications(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return librarianApplicationRepository.findAll(pageable);
    }

    public Application getApplicationById(Integer id) {
        return librarianApplicationRepository.findById(id).orElse(null);
    }

    public List<Application_Type> getAllApplicationTypes() {
        return librarianApplicationTypeRepository.findAll();
    }

    public void update(Application application) {
        // Check if the application exists
        Optional<Application> existingApplicationOptional = librarianApplicationRepository.findById(application.getId());
        System.out.println("Reason is "+application.getReason());

        if (existingApplicationOptional.isPresent()) {
            // Get the existing application to update its fields
            Application existingApplication = existingApplicationOptional.get();

            // Update fields based on the incoming `application` object
            existingApplication.setApplication_type(application.getApplication_type()); // or application_type field
            existingApplication.setStatus(application.getStatus());
            existingApplication.setResponse(application.getResponse());
            existingApplication.setUser(application.getUser());
            existingApplication.setBorrowIndex(application.getBorrowIndex());// response field
            // If necessary, update other fields as well, e.g., borrowIndex, user, etc.

            // Save the updated application back to the repository
            librarianApplicationRepository.save(existingApplication);
        }
    }

}
