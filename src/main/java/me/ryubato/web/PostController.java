package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String home() {
        return "redirect:/posts/postList";
    }

    @GetMapping("/posts/postList")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "posts/postList";
    }

}
