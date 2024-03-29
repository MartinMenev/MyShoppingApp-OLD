package com.example.myshoppingapp.domain.comments;

import com.example.myshoppingapp.domain.BaseEntity;
import com.example.myshoppingapp.domain.recipes.Recipe;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private LocalDateTime dateAdded;

    @Column
    private long rating;

   @ManyToOne
<<<<<<< HEAD
    private UserEntity author;
=======
    private User author;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

    @ManyToOne
    private Recipe recipe;

    public Comment() {
        this.dateAdded = LocalDateTime.now();
    }

    public String getAuthorPicUrl() {

        String url = this.getAuthor().getPicture().getPictureUrl();
        if (url == null) {
            return "/images/default-user-pic.jpg";
        }

        return "/images/"+url;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

<<<<<<< HEAD
    public Comment setAuthor(UserEntity author) {
=======
    public Comment setAuthor(User author) {
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        this.author = author;
        return this;
    }

    public Comment setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public Comment setRating(long rating) {
        this.rating = rating;
        return this;
    }
}
