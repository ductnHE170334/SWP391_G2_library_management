package SWP391_G2.com.example.library_Management.Staff.ContentWriter.service;

import SWP391_G2.com.example.library_Management.Entity.News;
import SWP391_G2.com.example.library_Management.Staff.ContentWriter.repository.ContentWriterNewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentWriterNewsService {
    @Autowired
    private ContentWriterNewsRepository contentWriterNewsRepository;

    public List<News> getAllNews(){
        return contentWriterNewsRepository.findAll();
    }

    public void deleteById(int id){
        contentWriterNewsRepository.deleteById(id);
    }

    public List<News> findNewsByTitle(String title){
        return contentWriterNewsRepository.findNewsByTitle(title);
    }
}
