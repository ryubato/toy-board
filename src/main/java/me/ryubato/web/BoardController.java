package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardApiController boardApiController;

    @RequestMapping("/boards")
    private String getBoards(Model model) {
        List<BoardListDto> boardListDtos = boardApiController.findAll();
        model.addAttribute("boards", boardListDtos);
        return "boards";
    }
}
