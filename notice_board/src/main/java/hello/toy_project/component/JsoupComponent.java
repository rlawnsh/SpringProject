package hello.toy_project.component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import hello.toy_project.controller.dto.KospiStockDto;
import hello.toy_project.controller.dto.StockNewsDto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class JsoupComponent {

    public List<KospiStockDto> getKosPiStockList() {
        final String stockList = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
        Connection conn = Jsoup.connect(stockList);
        try {
            Document document = conn.get();
            return getKosPiStockList(document);
        } catch (IOException ignored) {
        }
        return null;
    }

    public List<KospiStockDto> getKosPiStockList(Document document) {
        Elements kosPiTable = document.select("table.type_2 tbody tr");
        List<KospiStockDto> list = new ArrayList<>();
        for (Element element : kosPiTable) {
            if (element.attr("onmouseover").isEmpty()) {
                continue;
            }
            list.add(createKosPiStockDto(element.select("td")));
        }
        return list;
    }

    public KospiStockDto createKosPiStockDto(Elements td) {
        KospiStockDto kospiStockDto = KospiStockDto.builder().build();
        Class<?> clazz = kospiStockDto.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < td.size(); i++) {
            String text;
            if(td.get(i).select(".center a").attr("href").isEmpty()){
                text = td.get(i).text();
            }else{
                text = "https://finance.naver.com" + td.get(i).select(".center a").attr("href");
            }
            fields[i].setAccessible(true);
            try{
                fields[i].set(kospiStockDto,text);
            }catch (Exception ignored){
            }
        }
        return kospiStockDto;
    }

    public List<StockNewsDto> getStockNewsList() {
        final String newsList = "https://finance.naver.com/news/news_list.naver?mode=LSS3D&section_id=101&section_id2=258&section_id3=401";
        Connection conn = Jsoup.connect(newsList);
        try {
            Document document = conn.get();
            return getStockNewsList(document);
        } catch (IOException e) {
        }
        return null;
    }

    private List<StockNewsDto> getStockNewsList(Document document) {
        List<StockNewsDto> stockNewsDtos = new ArrayList<>();
        Elements articleSubject = document.select("dd.articleSubject");
        for (int i = 0; i < articleSubject.size(); i++) {
            StockNewsDto stockNewsDto = new StockNewsDto();
            stockNewsDto.setArticleUrl("https://finance.naver.com" + articleSubject.select("a").get(i).attr("href"));
            stockNewsDto.setArticleSubject(articleSubject.select("a").get(i).attr("title"));
            stockNewsDtos.add(stockNewsDto);
        }
        return stockNewsDtos;
    }
}