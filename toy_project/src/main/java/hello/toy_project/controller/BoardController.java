package hello.toy_project.controller;

import hello.toy_project.entity.Board;
import hello.toy_project.repository.BoardRepository;
import hello.toy_project.service.BoardService;
import hello.toy_project.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardValidator boardValidator;
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> findBoards = boardRepository.findAll(pageable);
        Page<Board> findBoards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = 1;
        int totalPages = findBoards.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("findBoards", findBoards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {

            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String post(@Valid Board new_board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(new_board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
//        System.out.println("new_board = " + new_board);
        Object details = authentication.getDetails();
        System.out.println("details = " + details);
        String username = authentication.getName();
        boardService.save(username, new_board);
        return "redirect:/board/list";
    }
}


