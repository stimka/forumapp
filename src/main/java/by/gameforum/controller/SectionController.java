package by.gameforum.controller;

import by.gameforum.controller.form.NewSectionForm;
import by.gameforum.model.Section;
import by.gameforum.model.User;
import by.gameforum.service.RoleService;
import by.gameforum.service.SectionService;
import by.gameforum.service.UserService;
import by.gameforum.model.Role;
import by.gameforum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/section/")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("{id}")
    public String getTopicsFromSection(@PathVariable int id,
                                       Model model) {
        model.addAttribute("section", sectionService.findById(id));
        model.addAttribute("topics", topicService.findBySection(id));
        return "section";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getNewSectionForm(Model model) {
        model.addAttribute("newSection", new NewSectionForm());
        return "new_section_form";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String processAndAddNewSection(
            @Valid @ModelAttribute("newSection") NewSectionForm newSection,
            BindingResult result) {

        if (result.hasErrors()) {
            return "new_section_form";
        }

        Section section = new Section();
        section.setName(newSection.getName());
        section.setDescription(newSection.getDescription());
        section = sectionService.save(section);
        return "redirect:/section/" + section.getId();
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id,
                         Authentication authentication,
                         RedirectAttributes model) {
        User user = userService.findByUsername(authentication.getName());
        Role adminRole = roleService.findByName("ADMIN");
        if (!user.getRoles().contains(adminRole)) {
            return "redirect:/section/" + id;
        }
        sectionService.delete(id);

        model.addFlashAttribute("message", "section.successfully.deleted");
        return "redirect:/home";
    }

}
