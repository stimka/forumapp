package by.zagursky.gameforum.model;

import java.util.Objects;
import java.util.Set;

/**
 * Created by Evgeny Yushkevich on 08.05.2017.
 */
public class UserProfile {

    private User user;

    private Set<Post> posts;

    private Set<Topic> topics;

    public UserProfile() {}

    public UserProfile(User user,
                       Set<Post> posts,
                       Set<Topic> topics) {
        super();
        this.user = user;
        this.posts = posts;
        this.topics = topics;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(posts, that.posts) &&
                Objects.equals(topics, that.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, posts, topics);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "user=" + user +
                ", posts=" + posts +
                ", topics=" + topics +
                '}';
    }
}
