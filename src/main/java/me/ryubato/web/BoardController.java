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
    public String index(Model model) {
        model.addAttribute("boards", boardService.getBoards());
        return "index";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "boards/detail";
    }
}
