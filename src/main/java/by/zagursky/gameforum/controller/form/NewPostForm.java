package by.zagursky.gameforum.controller.form;

import javax.validation.constraints.Size;

/**
 * Created by Evgeny Yushkevich on 09.05.2017.
 */
public class NewPostForm {

    @Size(min = 3, message = "{Size.Post.content.validation}")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
