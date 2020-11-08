package me.ryubato.board.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String home() {
        return "redirect:/boards";
    }

    @GetMapping("/boards")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "boards";
    }

}
