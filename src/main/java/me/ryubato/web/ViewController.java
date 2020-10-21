package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PostService postService;

    @GetMapping("/")
    public String home() {
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "board";
    }

}
