package SWP391_G2.com.example.library_Management.Staff.Librarian.service;

import SWP391_G2.com.example.library_Management.Entity.Status;
import SWP391_G2.com.example.library_Management.Staff.Librarian.repository.LibrarianStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianStatusService {

    private final LibrarianStatusRepository librarianStatusRepository;

    @Autowired
    public LibrarianStatusService(LibrarianStatusRepository theLibrarianStatusRepository) {
        this.librarianStatusRepository = theLibrarianStatusRepository;
    }

    // Get all status
    public List<Status> getStatus() {
        return librarianStatusRepository.findAll();
    }

    // Get a specific status by ID
    public Status getStatus(String id) {
        return librarianStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }

    // Update a status
    public Status updateStatus(String id, String newStatus) {
        Status status = getStatus(id);
        status.setStatus(newStatus); // Update the status with the new value
        return librarianStatusRepository.save(status);
    }

    // Delete a status by ID
    public void deleteStatus(String id) {
        librarianStatusRepository.deleteById(id);
    }

    // Add a new status
    public Status addStatus(String status) {
        Status newStatus = new Status();
        newStatus.setStatus(status); // Thiết lập tên trạng thái
        return librarianStatusRepository.save(newStatus);
    }
    // Search for status by name
    public List<Status> searchStatusByName(String status) {
        return librarianStatusRepository.findByStatusContainingIgnoreCase(status);
    }
}
