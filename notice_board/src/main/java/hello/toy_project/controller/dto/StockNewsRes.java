package hello.toy_project.controller.dto;

import hello.toy_project.entity.StockNews;
import lombok.Getter;

@Getter
public class StockNewsRes {

    private Long id;
    private String articleUrl;
    private String articleSubject;
    private Integer likes;
    private Integer dislikes;

    public StockNewsRes(StockNews stockNews) {
        this.id = stockNews.getId();
        this.articleUrl = stockNews.getArticleUrl();
        this.articleSubject = stockNews.getArticleSubject();
        this.likes = stockNews.getLikesList().size();
        this.dislikes = stockNews.getDislikesList().size();
    }
}
