package SWP391_G2.com.example.library_Management.Staff.ContentWriter.controller;

import SWP391_G2.com.example.library_Management.Entity.News;
import SWP391_G2.com.example.library_Management.Staff.ContentWriter.service.ContentWriterNewsService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class ContentWriterNewsManagementController {
    @Autowired
    private ContentWriterNewsService contentWriterNewsService;

    @GetMapping("/list")
    public String listNews(Model theModel, @Param("title") String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Page<News> newsPage;

        if(title != null) {
            newsPage = contentWriterNewsService.findNewsByTitle(title, page, size);
            theModel.addAttribute("searchtitle", title);
        } else {
            newsPage = contentWriterNewsService.getAllNews(page, size);
        }

        theModel.addAttribute("newsPage", newsPage);
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", newsPage.getTotalPages());

        return "Staff/dashboard/News/ManageNews";
    }

    //Show form to add new
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        News news = new News();

        theModel.addAttribute("news", news);

        return "Staff/dashboard/News/AddNews";
    }

    @PostMapping("/save")
    public String saveNew(@ModelAttribute("news") News news, @RequestParam("image") MultipartFile image) {
        // Check if the image is not empty
        if (!image.isEmpty()) {
            try {
                // Get the original filename
                String originalFilename = image.getOriginalFilename();
                // Create a unique filename using timestamp
                String newFilename = System.currentTimeMillis() + "_" + originalFilename;

                // Define the upload directory path relative to the application's root directory
                String uploadDir = "src/uploads/";
                Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

                // Create the directory if it does not exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file to the defined path
                Path filePath = uploadPath.resolve(newFilename);
                image.transferTo(filePath.toFile());

                // Set the file name in the news object (this will be stored in the database)
                news.setImage_url(newFilename);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";  // Handle the error
            }
        }

        // Set the current date and time
        news.setDate_created(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        // Save the news
        contentWriterNewsService.save(news);

        return "redirect:/news/list";
    }

    //Delete a new
    @GetMapping("/delete")
    public String delete(@RequestParam("newId") int id) {

        // delete the new
        contentWriterNewsService.deleteById(id);

        // redirect to /news/list
        return "redirect:/news/list";

    }

    //Show form to edit new
    @GetMapping("/showFormForEdit")
    public String showFormForEdit(@RequestParam("newId") int id, Model theModel){
        //Get news by Id
        News news = contentWriterNewsService.findById(id);

        theModel.addAttribute("news", news);

        // Add the image URL to the model
        if (news.getImage_url() != null) {
            String imageUrl = "/uploads/" + news.getImage_url();
            theModel.addAttribute("imageUrl", imageUrl);
        }

        return "Staff/dashboard/News/EditNews";
    }

}
