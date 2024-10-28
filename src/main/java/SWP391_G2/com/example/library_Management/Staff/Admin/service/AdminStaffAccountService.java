//package SWP391_G2.com.example.library_Management.Staff.Admin.service;
//
//import SWP391_G2.com.example.library_Management.Entity.Role;
//import SWP391_G2.com.example.library_Management.Entity.Staff;
//import SWP391_G2.com.example.library_Management.Staff.Admin.repository.AdminStaffAccountRepository;
//import SWP391_G2.com.example.library_Management.Staff.Admin.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminStaffAccountService {
//
//    private final AdminStaffAccountRepository adminStaffAccountRepository;
//    private final RoleRepository roleRepository;
//
//    // Constructor injection
//    @Autowired
//    public AdminStaffAccountService(AdminStaffAccountRepository adminStaffAccountRepository,
//                                    RoleRepository roleRepository) {
//        this.adminStaffAccountRepository = adminStaffAccountRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    // Get all staff with pagination
//    public Page<Staff> getStaff(Pageable pageable) {
//        return adminStaffAccountRepository.findAll(pageable);
//    }
//
//    // Get a specific staff by ID
//    public Staff getStaff(String id) {
//        return adminStaffAccountRepository.findById(Integer.valueOf(id))
//                .orElseThrow(() -> new RuntimeException("Staff not found"));
//    }
//
//    // Add new staff
//    public Staff addStaff(Staff staff) {
//        return adminStaffAccountRepository.save(staff);
//    }
//
//    // Update a staff
//    public Staff updateStaff(int id, Staff updatedStaff) {
//        return adminStaffAccountRepository.findById(id)
//                .map(staff -> {
//                    staff.setFirstName(updatedStaff.getFirstName());
//                    staff.setLastName(updatedStaff.getLastName());
//                    staff.setEmail(updatedStaff.getEmail());
//                    staff.setPhone(updatedStaff.getPhone());
//                    staff.setPassword(updatedStaff.getPassword());
//                    staff.setRole(updatedStaff.getRole());
//                    return adminStaffAccountRepository.save(staff);
//                })
//                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
//    }
//
//    // Delete staff by ID
//    public void deleteStaff(int id) {
//        adminStaffAccountRepository.deleteById(id);
//    }
//
//    // Search for staff by name with pagination
//    public Page<Staff> searchStaffByName(String name, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return adminStaffAccountRepository.findByLastNameContaining(name, pageable);
//    }
//
//    // Get role by staff id
//    public Role getRoleByStaffId(int staffId) {
//        Staff staff = adminStaffAccountRepository.findById(staffId)
//                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
//        return staff.getRole();
//    }
//    public List<Role> getAllRoles() {
//        return roleRepository.findAll();
//    }
//}
