package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home() {
        return "redirect:/boards/boardList";
    }

    @GetMapping("/boards/boardList")
    public String getBoards(Model model) {
        model.addAttribute("boards", boardService.getBoards());
        return "boards/boardList";
    }

    @GetMapping("/boards/new")
    public String newBoard(Model model) {
        BoardForm boardForm = BoardForm.builder().writer("관리자").build();
        model.addAttribute("boardForm", boardForm);
        return "boards/boardForm";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "boards/boardList";
    }
}
