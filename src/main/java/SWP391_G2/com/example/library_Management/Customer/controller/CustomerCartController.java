package SWP391_G2.com.example.library_Management.Customer.controller;

import SWP391_G2.com.example.library_Management.Customer.service.CustomerCartService;
import SWP391_G2.com.example.library_Management.Entity.Book_item;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    public String addToCart(@RequestParam Integer bookId,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        // Convert Integer to Long
        Long bookIdLong = bookId != null ? Long.valueOf(bookId) : null;
        Book_item bookItem = customerCartService.addToCart(bookIdLong);

        if (bookItem == null) {
            throw new RuntimeException("No available copies of this book!");
        }

        // Get the user ID from the session
        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;

        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

        // Debugging logs
        System.out.println("User ID from session: " + userId);

        if (userId == null) {
            throw new RuntimeException("User ID is null! Please log in again.");
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

        // Add new Book_item to cookie
        String newCookieValue = userId + "-" + bookItem.getId();
        if (!existingCookieValue.isEmpty()) {
            newCookieValue = existingCookieValue + "|" + newCookieValue;
        }

        Cookie cartCookie = new Cookie("cartItem", newCookieValue);
        cartCookie.setMaxAge(24 * 60 * 60);
        cartCookie.setPath("/");

        response.addCookie(cartCookie);

        return "redirect:/home/list";
    }




    @GetMapping("/delete")
    public String deleteItemFromCart(@CookieValue(value = "cartItem", defaultValue = "") String cartItem,
                                     HttpServletRequest request,
                                     @RequestParam Long bookItemId,
                                     HttpServletResponse response,
                                     Model model) {

        // Get the user ID from the session
        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;

        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

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
                           HttpServletRequest request,
                           Model model) {
        List<Book_item> bookItemList = new ArrayList<>();

        // Get the user ID from the session
        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;

        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

        if (!cartItem.isEmpty()) {
            String[] pairs = cartItem.split("\\|");
            for (String pair : pairs) {
                String[] parts = pair.split("-");
                Long cookieCustomerId = Long.valueOf(parts[0]);
                Long bookItemId = Long.valueOf(parts[1]);

                if (cookieCustomerId.equals(userId)) {
                    Book_item bookItem = customerCartService.getBookItemById(bookItemId);
                    bookItemList.add(bookItem);
                }
            }
        }

        model.addAttribute("bookItemList", bookItemList);
        model.addAttribute("customerId", userId);

        return "Customer/RequestBorrow/checkOutBook";
    }

    @PostMapping("/cart-page")
    public String submitBorrowRequest(@CookieValue(value = "cartItem", defaultValue = "") String cartItem,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      Model model) {

        // Get the user ID from the session
        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        Long userId = null;

        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = Long.valueOf((Integer) userIdObj);
        }

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
        return "redirect:/home/list";
    }
}
