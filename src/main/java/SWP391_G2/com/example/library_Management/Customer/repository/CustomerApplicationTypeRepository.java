package SWP391_G2.com.example.library_Management.Customer.repository;

import SWP391_G2.com.example.library_Management.Entity.Application_Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerApplicationTypeRepository extends JpaRepository<Application_Type,Integer> {
    List<Application_Type> findAll();
}
