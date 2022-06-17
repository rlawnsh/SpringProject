package hello.toy_project.entity;

import hello.toy_project.controller.dto.StockNewsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StockNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleUrl;
    private String articleSubject;

    @OneToMany(mappedBy = "stockNews")
    private List<Likes> likesList;

    @OneToMany(mappedBy = "stockNews")
    private List<Dislikes> dislikesList;

    public StockNews(StockNewsDto stockNewsDto) {
        this.articleUrl = stockNewsDto.getArticleUrl();
        this.articleSubject = stockNewsDto.getArticleSubject();
    }
}
