package com.example.myshoppingapp.service;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import com.example.myshoppingapp.domain.comments.Comment;
import com.example.myshoppingapp.domain.comments.InputCommentDTO;
import com.example.myshoppingapp.domain.comments.OutputCommentDTO;
import com.example.myshoppingapp.domain.recipes.Recipe;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import com.example.myshoppingapp.repository.CommentRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RecipeService recipeService;
    private final LoggedUser loggedUser;


    @Autowired
    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper, UserService userService, RecipeService recipeService, LoggedUser loggedUser) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.recipeService = recipeService;
        this.loggedUser = loggedUser;
    }


    public void addComment(InputCommentDTO inputCommentDTO, Long recipeId) {
        inputCommentDTO.setId(null);
        Comment comment = modelMapper.map(inputCommentDTO, Comment.class);
<<<<<<< HEAD
        UserEntity author = this.userService.findByUsername(this.loggedUser.getUsername());
=======
        User author = this.userService.findByUsername(this.loggedUser.getUsername());
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        Recipe recipe = this.recipeService.getRecipeRepository().getById(recipeId);
        comment.setRecipe(recipe);
        if (author != null) {
            comment.setAuthor(author);
            this.commentRepository.saveAndFlush(comment);
        }
    }

    public List<OutputCommentDTO> showAllComments(Long recipeId) {
        return this.commentRepository
                .findAllByRecipeIdOrderByIdDesc(recipeId)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(comment -> this.modelMapper.map(comment, OutputCommentDTO.class))
                .toList();
    }
    public List<OutputCommentDTO> showLatestComments(Long recipeId) {
        return showAllComments(recipeId)
                .stream()
                .limit(3)
                .toList();
    }

    public List<OutputCommentDTO> showTopRatedComments() {
        return this.commentRepository
                .findAll()
                .stream()
                .map(comment -> modelMapper.map(comment, OutputCommentDTO.class))
                .sorted((a, b) -> b.getRating().compareTo(a.getRating()))
                .limit(4)
                .toList();
    }
}
