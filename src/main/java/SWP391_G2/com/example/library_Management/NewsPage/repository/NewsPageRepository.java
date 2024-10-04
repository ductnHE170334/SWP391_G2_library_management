package SWP391_G2.com.example.library_Management.NewsPage.repository;

import SWP391_G2.com.example.library_Management.Entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsPageRepository extends JpaRepository<News, Integer> {

    @Query("SELECT n FROM News n WHERE n.title like %?1%")
    Page<News> findNewsByTitle(String title, Pageable pageable);
}
