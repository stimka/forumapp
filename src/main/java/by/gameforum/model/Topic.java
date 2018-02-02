package by.gameforum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "topic")
public class Topic implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "views")
    private int views;

    @Column(name = "creation_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "is_closed")
    private boolean closed;

    @Column(name = "id_topic_picture")
    private int idTopicPicture;

    public Topic() {
    }

    public Topic(User user, Section section, String title, String content, int views, Date creationDate, Date lastUpdateDate, boolean closed, int idTopicPicture) {
        this.user = user;
        this.section = section;
        this.title = title;
        this.content = content;
        this.views = views;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.closed = closed;
        this.idTopicPicture = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getIdTopicPicture() {
        return idTopicPicture;
    }

    public void setIdTopicPicture(int idTopicPicture) {
        this.idTopicPicture = idTopicPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id &&
                views == topic.views &&
                closed == topic.closed &&
                idTopicPicture == topic.idTopicPicture &&
                Objects.equals(user, topic.user) &&
                Objects.equals(section, topic.section) &&
                Objects.equals(title, topic.title) &&
                Objects.equals(content, topic.content) &&
                Objects.equals(creationDate, topic.creationDate) &&
                Objects.equals(lastUpdateDate, topic.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, section, title, content, views, creationDate, lastUpdateDate, closed, idTopicPicture);
    }
}
