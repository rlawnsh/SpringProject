package hello.toy_project.repository;

import hello.toy_project.entity.StockNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockNewsRepository extends JpaRepository<StockNews, Long> {
    StockNews findByArticleSubject(String articleSubject);
}
