package SWP391_G2.com.example.library_Management.Staff.ContentWriter.controller;

import SWP391_G2.com.example.library_Management.Entity.News;
import SWP391_G2.com.example.library_Management.Staff.ContentWriter.service.ContentWriterNewsService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/news")
public class ContentWriterNewsManagementController {
    @Autowired
    private ContentWriterNewsService contentWriterNewsService;

    //List all news
    @GetMapping("/list")
    public String listNews(Model theModel, @Param("title") String title){
        //Get list from db
        List<News> news = contentWriterNewsService.getAllNews();

        if(title != null) {
            news = contentWriterNewsService.findNewsByTitle(title);
            theModel.addAttribute("searchtitle", title);
        }

        //Add to spring model
        theModel.addAttribute("news", news);

        return "Staff/dashboard/News/ManageNews";
    }

    //Delete a new
    @GetMapping("/delete")
    public String delete(@RequestParam("newId") int id) {

        // delete the new
        contentWriterNewsService.deleteById(id);

        // redirect to /news/list
        return "redirect:/news/list";

    }

}
