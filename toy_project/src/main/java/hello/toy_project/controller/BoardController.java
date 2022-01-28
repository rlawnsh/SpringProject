package hello.toy_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class BoardController {

    @GetMapping("board/list")
    public String list() {
        return "board/list";
    }
}


