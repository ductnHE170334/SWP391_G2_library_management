package SWP391_G2.com.example.library_Management.Staff.ContentWriter.controller;

import SWP391_G2.com.example.library_Management.Staff.ContentWriter.service.ContentWriterNewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class ContentWriterNewsManagementController {
    @Autowired
    private ContentWriterNewsService contentWriterNewsService;

}
