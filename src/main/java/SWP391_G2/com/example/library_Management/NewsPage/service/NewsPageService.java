//package SWP391_G2.com.example.library_Management.NewsPage.service;
//
//import SWP391_G2.com.example.library_Management.Entity.News;
//import SWP391_G2.com.example.library_Management.NewsPage.repository.NewsPageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NewsPageService {
//    @Autowired
//    private NewsPageRepository newsPageRepository;
//
//    public Page<News> getAllNews(int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return newsPageRepository.findAll(pageable);
//    }
//
//    public Page<News> findNewsByTitle(String title, int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return newsPageRepository.findNewsByTitle(title, pageable);
//    }
//}
