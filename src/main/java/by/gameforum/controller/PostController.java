package by.gameforum.controller;

import by.gameforum.service.PostService;
import by.gameforum.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id,
                         Authentication authentication,
                         RedirectAttributes model) {
        Post post = postService.findById(id);
        if (post == null || authentication == null || authentication.getName() == null
                || !authentication.getName().equals(post.getUser().getUsername())) {
            return "redirect:/";
        }

        postService.delete(post);

        model.addFlashAttribute("message", "post.successfully.deleted");
        return "redirect:/topic/" + post.getTopic().getId();
    }
}
