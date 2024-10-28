//package SWP391_G2.com.example.library_Management.Staff.ContentWriter.service;
//
//import SWP391_G2.com.example.library_Management.Entity.News;
//import SWP391_G2.com.example.library_Management.Staff.ContentWriter.repository.ContentWriterNewsRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ContentWriterNewsService {
//    @Autowired
//    private ContentWriterNewsRepository contentWriterNewsRepository;
//
//    public Page<News> getAllNews(int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return contentWriterNewsRepository.findAll(pageable);
//    }
//
//    public void deleteById(int id){
//        contentWriterNewsRepository.deleteById(id);
//    }
//
//    public News findById(int id) {
//        Optional<News> result = contentWriterNewsRepository.findById(id);
//
//        News news = null;
//
//        if (result.isPresent()) {
//            news = result.get();
//        }
//        else {
//            // we didn't find the employee
//            throw new RuntimeException("Did not find new id - " + id);
//        }
//
//        return news;
//    }
//
//    public Page<News> findNewsByTitle(String title, int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return contentWriterNewsRepository.findNewsByTitle(title, pageable);
//    }
//
//    public void save(News news) {
//        contentWriterNewsRepository.save(news);
//    }
//}
