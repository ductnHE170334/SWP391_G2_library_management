package SWP391_G2.com.example.library_Management.Staff.ContentWriter.repository;

import SWP391_G2.com.example.library_Management.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentWriterNewsRepository extends JpaRepository<News, Integer> {

    @Query("SELECT n FROM News n WHERE n.title like %?1%")
    List<News> findNewsByTitle(String title);
}
