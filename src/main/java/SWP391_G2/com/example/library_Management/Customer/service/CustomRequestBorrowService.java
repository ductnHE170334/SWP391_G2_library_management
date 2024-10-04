package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.BorrowHistoryRepository;
import SWP391_G2.com.example.library_Management.Entity.Borrow_history;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomRequestBorrowService implements ICustomerRequestBorrow{
    private final BorrowHistoryRepository repository;
    @Override
    public Page<Borrow_history> getList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void addBorrowRequest(Borrow_history borrow_history) {
        repository.save(borrow_history);
    }

    @Override
    public Borrow_history getBorrowRequest(int id) {
        return repository.findById(id).orElse(null);
    }
}
