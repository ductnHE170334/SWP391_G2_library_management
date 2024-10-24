package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerCartService;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CustomerCartController {
    @Autowired
    private CustomerCartService customerCartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long bookId,
                            @RequestParam("userId") Long userId,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        Book_item bookItem = customerCartService.addToCart(bookId);

        if (bookItem == null) {
            throw new RuntimeException("No available copies of this book!");
        }

        String existingCookieValue = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cartItem".equals(cookie.getName())) {
                    existingCookieValue = cookie.getValue();
                    break;
                }
            }
        }

        // Thêm Book_item mới vào cookie
        String newCookieValue = userId + "-" + bookItem.getId();
        if (!existingCookieValue.isEmpty()) {
            newCookieValue = existingCookieValue + "|" + newCookieValue;
        }

        Cookie cartCookie = new Cookie("cartItem", newCookieValue);
        cartCookie.setMaxAge(24 * 60 * 60);
        cartCookie.setPath("/");

        response.addCookie(cartCookie);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteItemFromCart(@CookieValue(value = "cartItem", defaultValue = "") String cartItem,
                                     @RequestParam Long userId,
                                     @RequestParam Long bookItemId,
                                     HttpServletResponse response,
                                     Model model) {

        if (!cartItem.isEmpty()) {
            String[] pairs = cartItem.split("\\|");
            StringBuilder updatedCart = new StringBuilder();

            for (String pair : pairs) {
                String[] parts = pair.split("-");
                Long cookieCustomerId = Long.valueOf(parts[0]);
                Long cookieBookItemId = Long.valueOf(parts[1]);


                if (!(cookieCustomerId.equals(userId) && cookieBookItemId.equals(bookItemId))) {

                    if (updatedCart.length() > 0) {
                        updatedCart.append("|");
                    }
                    updatedCart.append(pair);
                }
            }

            Cookie updatedCookie = new Cookie("cartItem", updatedCart.toString());
            updatedCookie.setPath("/");
            updatedCookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(updatedCookie);
        }


        return "redirect:/cart";
    }



    @GetMapping
    public String viewCart(@CookieValue(value = "cartItem", defaultValue = "") String cartItem,
                           @RequestParam(defaultValue = "1") Long userId,
                           Model model) {
//        List<Book> books = new ArrayList<>();
        List<Book_item> bookItemList = new ArrayList<>();

        if (!cartItem.isEmpty()) {
            String[] pairs = cartItem.split("\\|");
            for (String pair : pairs) {
                String[] parts = pair.split("-");
                Long cookieCustomerId = Long.valueOf(parts[0]);
                Long bookItemId = Long.valueOf(parts[1]);

                if (cookieCustomerId.equals(userId)) {
                    Book_item bookItem = customerCartService.getBookItemById(bookItemId);
                    bookItemList.add(bookItem);
//                    Book book = customerCartService.getBookFromBookItem(bookItemId);
//                    if (book != null) {
//                        books.add(book);
//                    }
                }
                model.addAttribute("bookItemList",bookItemList);
                model.addAttribute("customerId",cookieCustomerId);

            }
        }

//        if (books.isEmpty()) {
//            model.addAttribute("message", "No items in cart.");
//        } else {
//            model.addAttribute("books", books);
//        }

        return "Customer/RequestBorrow/checkOutBook";
    }


    @PostMapping("/cart-page")
    public String submitBorrowRequest(@CookieValue(value = "cartItem", defaultValue = "") String cartItem,
                                      @RequestParam("userId") Long userId,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      Model model) {

        if (!cartItem.isEmpty()) {
            String[] pairs = cartItem.split("\\|");
            StringBuilder updatedCookieValue = new StringBuilder();

            for (String pair : pairs) {
                String[] parts = pair.split("-");
                Long cookieCustomerId = Long.valueOf(parts[0]);
                Long bookItemId = Long.valueOf(parts[1]);

                // Add request if the customerId matches
                if (cookieCustomerId.equals(userId)) {
                    customerCartService.addBookRequest(cookieCustomerId, bookItemId);
                } else {
                    // If the customerId doesn't match, retain this item in the cookie
                    if (updatedCookieValue.length() > 0) {
                        updatedCookieValue.append("|");
                    }
                    updatedCookieValue.append(pair);
                }
            }

            // Update or delete the cookie based on whether there are remaining items
            if (updatedCookieValue.length() > 0) {
                Cookie updatedCartCookie = new Cookie("cartItem", updatedCookieValue.toString());
                updatedCartCookie.setMaxAge(24 * 60 * 60);
                updatedCartCookie.setPath("/");
                response.addCookie(updatedCartCookie);
            } else {
                // If no items remain, delete the cookie
                Cookie cartCookieToDelete = new Cookie("cartItem", null);
                cartCookieToDelete.setMaxAge(0); 
                cartCookieToDelete.setPath("/");
                response.addCookie(cartCookieToDelete);
            }
        }

        // Notify the user
        model.addAttribute("message", "Your request has been successfully submitted.");
        return "redirect:/";
    }
}
