package SWP391_G2.com.example.library_Management.Controller.DemoController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the home page!");
        return "test/home";
    }

    @GetMapping("/helloworld")
    public String helloWorld(Model model) {
        model.addAttribute("theDate", new java.util.Date());
        return "test/helloWorld";
    }
}
