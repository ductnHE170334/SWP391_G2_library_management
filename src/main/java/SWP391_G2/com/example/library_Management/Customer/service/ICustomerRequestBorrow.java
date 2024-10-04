package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Entity.Borrow_history;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerRequestBorrow {
    public Page<Borrow_history> getList(Pageable pageable);
    public void addBorrowRequest(Borrow_history borrow_history);
    public Borrow_history getBorrowRequest(int id);
}

