package by.zagursky.gameforum.controller;

import by.zagursky.gameforum.controller.form.NewTopicForm;
import by.zagursky.gameforum.model.Topic;
import by.zagursky.gameforum.service.UserService;
import by.zagursky.gameforum.controller.form.NewPostForm;
import by.zagursky.gameforum.model.Post;
import by.zagursky.gameforum.service.PostService;
import by.zagursky.gameforum.service.SectionService;
import by.zagursky.gameforum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/topic/")
public class TopicController {

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private UserService userService;

    private static final String IMAGES = "resources/public/img/tp";
    private static final String TOMCAT_HOME_PROPERTY = "catalina.home";
    private static final String TOMCAT_HOME_PATH = System.getProperty(TOMCAT_HOME_PROPERTY);
    private static final String IMAGES_PATH = TOMCAT_HOME_PATH + File.separator + IMAGES;

    private static final File IMAGES_DIR = new File(IMAGES_PATH);
    private static final String IMAGES_DIR_ABSOLUTE_PATH = IMAGES_DIR.getAbsolutePath() + File.separator;


    @RequestMapping(value = "{idTopic}", method = RequestMethod.GET)
    public String getTopicById(@PathVariable int idTopic,
                               Model model) {
        Topic topic = topicService.findById(idTopic);
        topic.setViews(topic.getViews() + 1);
        topicService.save(topic);

        model.addAttribute("topic", topic);
        model.addAttribute("posts", postService.findByTopic(idTopic));
        model.addAttribute("newPost", new NewPostForm());
        return "topic";
    }



    @RequestMapping(value = "{idTopic}", method = RequestMethod.POST)
    public String addPost(@Valid @ModelAttribute("newPost") NewPostForm newPost,
                          BindingResult result,
                          Authentication authentication,
                          @PathVariable int idTopic,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("topic", topicService.findById(idTopic));
            model.addAttribute("posts", postService.findByTopic(idTopic));
            return "topic";
        }

        Post post = new Post();
        post.setContent(newPost.getContent());
        post.setTopic(topicService.findById(idTopic));
        post.setUser(userService.findByUsername(authentication.getName()));
        postService.save(post);

        model.asMap().clear();
        return "redirect:/topic/" + idTopic;
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getNewTopictForm(Model model) {
        model.addAttribute("newTopic", new NewTopicForm());
        model.addAttribute("sections", sectionService.findAll());
        return "new_topic_form";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String processAndAddNewTopic(@Valid @ModelAttribute("newTopic") NewTopicForm newTopic,
                                        @RequestPart MultipartFile topicPicture,
                                        HttpServletRequest request,
                                        BindingResult result,
                                        Authentication authentication,
                                        Model model) {

        if (result.hasErrors()) {
            model.addAttribute("sections", sectionService.findAll());
            return "new_topic_form";
        }

        Topic topic = new Topic();
        topic.setUser(userService.findByUsername(authentication.getName()));
        topic.setTitle(newTopic.getTitle());
        topic.setContent(newTopic.getContent());
        topic.setSection(sectionService.findById(newTopic.getSectionId()));

        topic = topicService.save(topic);
        topic.setIdTopicPicture(topic.getId());
        topicService.save(topic);

        try {
            if (!topicPicture.isEmpty()) {
                String path =
                        request.getSession().getServletContext().getRealPath("/resources/public/");
                File file = new File(path + File.separator + "images");
                file.mkdir();
                System.out.println(path);
                topicPicture.transferTo(new File(path + File.separator  + "images" + File.separator + topic.getId() + ".jpg"));
            }
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return "redirect:/topic/" + topic.getId();
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id,
                         Authentication authentication,
                         RedirectAttributes model) {
        Topic topic = topicService.findById(id);

        if (topic == null) {
            return "redirect:/";
        }
        if (!authentication.getName().equals(topic.getUser().getUsername())) {
            return "redirect:/topic/" + id;
        }

        Topic topic1 = new Topic();
        topic1.setUser(topic.getUser());
        topic1.setTitle(topic.getTitle());
        topic1.setContent(topic.getContent());
        topic1.setSection(topic.getSection());
        topic1.setClosed(true);

        topic1 = topicService.save(topic1);
        topic1.setIdTopicPicture(topic.getId());

        topicService.save(topic1);
        topicService.delete(topic);

        model.addFlashAttribute("message", "topic.successfully.deleted");
        return "redirect:/section/" + topic.getSection().getId();
    }

}
