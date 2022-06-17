package hello.toy_project.repository;

import hello.toy_project.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findByStockNewsId(Long id);
}
