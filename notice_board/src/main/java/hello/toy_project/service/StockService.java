package hello.toy_project.service;

import java.util.List;

import hello.toy_project.component.JsoupComponent;
import hello.toy_project.controller.dto.KospiStockDto;
import hello.toy_project.controller.dto.StockNewsDto;
import hello.toy_project.entity.Dislikes;
import hello.toy_project.entity.Likes;
import hello.toy_project.entity.StockNews;
import hello.toy_project.entity.User;
import hello.toy_project.repository.DislikesRepository;
import hello.toy_project.repository.LikesRepository;
import hello.toy_project.repository.StockNewsRepository;
import hello.toy_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final JsoupComponent jsoupComponent;
    private final DislikesRepository dislikesRepository;
    private final LikesRepository likesRepository;
    private final StockNewsRepository stockNewsRepository;
    private final UserRepository userRepository;

    public List<KospiStockDto> getKosPiStockList() {
        return jsoupComponent.getKosPiStockList();
    }

    public List<StockNewsDto> getStockNewsList() {
        return jsoupComponent.getStockNewsList();
    }

    public void saveStockNews(StockNewsDto stockNewsDto) {
        StockNews stockNews = new StockNews(stockNewsDto);
        stockNewsRepository.save(stockNews);
    }

    public List<StockNews> getAllStockNews() {
        List<StockNews> all = stockNewsRepository.findAll();
        for (StockNews stockNews : all) {
            stockNews.setLikesList(likesRepository.findByStockNewsId(stockNews.getId()));
            stockNews.setDislikesList(dislikesRepository.findByStockNewsId(stockNews.getId()));
        }
        return all;
    }

    public Boolean getStockNews(String articleSubject) {
        if (stockNewsRepository.findByArticleSubject(articleSubject) != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void saveLike(Long newsId, String name) {
        User byUsername = userRepository.findByUsername(name);
        StockNews stockNews = stockNewsRepository.findById(newsId).orElse(null);
        Likes likes = new Likes(stockNews, byUsername);
        likesRepository.save(likes);
    }

    public void saveDislike(Long newsId, String name) {
        User byUsername = userRepository.findByUsername(name);
        StockNews stockNews = stockNewsRepository.findById(newsId).orElse(null);
        Dislikes dislikes = new Dislikes(stockNews, byUsername);
        dislikesRepository.save(dislikes);
    }

}