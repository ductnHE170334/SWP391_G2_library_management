package SWP391_G2.com.example.library_Management.Customer.service;

import SWP391_G2.com.example.library_Management.Customer.repository.*;
import SWP391_G2.com.example.library_Management.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class CustomerCartService {
    @Autowired
    private CustomerBookItemRepository customerBookItemRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerBorrowIndexRepository customerBorrowIndexRepository;
    @Autowired
    private CustomerBookRepository customerBookRepository;
    @Autowired
    private CustomerBorrowStatusRepository customerBorrowStatusRepository;
    public Book_item addToCart(Long bookId) {
        // Lấy danh sách các Book_item có sẵn
        List<Book_item> availableBookItems = customerBookItemRepository.findByBookIdAndCondition(bookId, "Available");

        // In ra thông tin cho debugging
        System.out.println("Number of available items: " + availableBookItems.size());

        if (availableBookItems.isEmpty()) {
            throw new RuntimeException("No available copies of this book!");
        }

        // Chọn một Book_item ngẫu nhiên từ danh sách
        return availableBookItems.get(new Random().nextInt(availableBookItems.size()));
    }


    public Book getBookFromBookItem(Long bookItemId) {
        Book_item bookItem = customerBookItemRepository.findById(String.valueOf(bookItemId))
                .orElseThrow(() -> new RuntimeException("Book Item not found"));

        // Lấy bookId từ bookItem
        int bookId = bookItem.getBook().getId();

        // Trả về thông tin của Book
        return customerBookRepository.findById(String.valueOf(bookId))
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void addBookRequest(Long cookieCustomerId, Long bookItemId) {
        System.out.println("============Start to add Book request============");
        Borrow_index borrowIndex = new Borrow_index();
        Customer customer = customerRepository.findById(String.valueOf(cookieCustomerId)).get();
        System.out.println("Name of customer: " + customer.getLastName());
        Book_item bookItem = customerBookItemRepository.findById(String.valueOf(bookItemId)).get();
        System.out.println("Book item "+ bookItem.getBook().getName());
        borrowIndex.setCustomer(customer);
        borrowIndex.setBook_item_id(bookItem);
        borrowIndex.setBorrow_date(LocalDateTime.now());
        LocalDateTime returnDate = LocalDateTime.now().plusDays(7);
        borrowIndex.setReturn_date(returnDate);
        Status status = customerBorrowStatusRepository.findById(String.valueOf(1)).get();
        borrowIndex.setStatus_id(status);
        customerBorrowIndexRepository.save(borrowIndex);
        System.out.println("============End to add Book request============");

    }

    public Book_item getBookItemById(Long bookItemId) {
        return customerBookItemRepository.findById(String.valueOf(bookItemId)).get();
    }
}
