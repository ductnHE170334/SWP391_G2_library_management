package SWP391_G2.com.example.library_Management.HomePage.controller;

import SWP391_G2.com.example.library_Management.Entity.Author;
import SWP391_G2.com.example.library_Management.HomePage.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/list")
    public String homePage(Model theModel) {
        //get the author from the service
        List<Author> theAuthors = homePageService.getAllAuthors();

        // add to the spring model
        theModel.addAttribute("authors", theAuthors);

        return "Customer/HomePage/HomePage";
    }
}
