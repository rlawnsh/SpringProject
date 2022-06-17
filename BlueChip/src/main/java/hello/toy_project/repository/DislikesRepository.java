package hello.toy_project.repository;

import hello.toy_project.entity.Dislikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DislikesRepository extends JpaRepository<Dislikes,Long> {
    List<Dislikes> findByStockNewsId(Long id);
}
