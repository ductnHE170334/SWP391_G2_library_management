package SWP391_G2.com.example.library_Management.Staff.ContentWriter.repository;

import SWP391_G2.com.example.library_Management.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentWriterNewsRepository extends JpaRepository<News, String> {
}