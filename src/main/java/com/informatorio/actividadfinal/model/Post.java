package com.informatorio.actividadfinal.model;
import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import jdk.jfr.BooleanFlag;

import java.util.List;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "title")
	private String title;

    @Column(name = "description")
	private String description;

	@Column(name = "body")
	private String body;

    @Column(name="creation_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
    
    @JsonIgnore
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments;

    @Column(name="published")
    @BooleanFlag
    private Boolean published;

    public User getUser() {
        return user;
    }
    public String getBody() {
        return body;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public Boolean getPublished() {
        return published;
    }
    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    
    public void setAuthor(User author) {
        this.user = author;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public void setPublished(Boolean published) {
        this.published = published;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
