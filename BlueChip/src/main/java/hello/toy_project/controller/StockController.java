package hello.toy_project.controller;

import java.util.List;
import java.util.stream.Collectors;

import hello.toy_project.controller.dto.KospiStockDto;
import hello.toy_project.controller.dto.StockNewsDto;
import hello.toy_project.controller.dto.StockNewsRes;
import hello.toy_project.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/kospi/all")
    public String getKosPiStockList(Model model) {
        List<KospiStockDto> kosPiStockList = stockService.getKosPiStockList();
        model.addAttribute("stockList", kosPiStockList);
        return "kospi/list";
    }

    @GetMapping("/news/all")
    public String getStockNewsList(Model model) {
        List<StockNewsDto> stockNewsList = stockService.getStockNewsList();
        for (StockNewsDto stockNewsDto : stockNewsList) {
            if (stockService.getStockNews(stockNewsDto.getArticleSubject())) {
                continue;
            }
            stockService.saveStockNews(stockNewsDto);
        }
        List<StockNewsRes> collect = stockService.getAllStockNews().stream().map(stockNews -> new StockNewsRes(stockNews)).collect(Collectors.toList());

        model.addAttribute("newsList", collect);
        return "kospi/newsList";
    }

    @PostMapping("/news/like")
    public void newsLike(Long newsId, Authentication authentication) {
        stockService.saveLike(newsId, authentication.getName());
    }

    @PostMapping("/news/dislike")
    public void newsDislike(Long newsId, Authentication authentication) {
        stockService.saveDislike(newsId, authentication.getName());
    }
}