package by.zagursky.gameforum.controller.form;

import by.zagursky.gameforum.model.Sex;

import javax.validation.constraints.Pattern;
import java.text.ParseException;

public class UserEditForm {

    @Pattern(regexp = "^\\p{IsAlphabetic}*$", message = "{Pattern.User.name.validation}")
    private String name;

    @Pattern(regexp = "^\\p{IsAlphabetic}*$", message = "{Pattern.User.name.validation}")
    private String lastName;

    private Sex sex;

    private String city;

    private String birthday;

    public UserEditForm() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) throws ParseException {
        this.birthday = birthday;
    }

}
