package hello.toy_project.service;

import java.util.List;

import hello.toy_project.component.JsoupComponent;
import hello.toy_project.controller.dto.KospiStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final JsoupComponent jsoupComponent;

    public List<KospiStockDto> getKosPiStockList() {
        return jsoupComponent.getKosPiStockList();
    }
}