package hello.toy_project.controller;

import java.util.List;

import hello.toy_project.controller.dto.KospiStockDto;
import hello.toy_project.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}