package SWP391_G2.com.example.library_Management.Staff.ContentWriter.service;

import SWP391_G2.com.example.library_Management.Staff.ContentWriter.repository.ContentWriterNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentWriterNewsService {
    @Autowired
    private ContentWriterNewsRepository contentWriterNewsRepository;
}
