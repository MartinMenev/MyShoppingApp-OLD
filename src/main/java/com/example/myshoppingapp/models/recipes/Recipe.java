package com.example.myshoppingapp.models.recipes;

import com.example.myshoppingapp.models.comments.Comment;
import com.example.myshoppingapp.models.enums.Category;
import com.example.myshoppingapp.models.pictures.Picture;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.models.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private LocalDate dateAdded;

   @Column(scale = 1, precision = 2)
    private double rating;

   @ElementCollection
   private List<Double> ratingList;

   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
   @JoinColumn
   private User author;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn
   private Picture picture;

   @Column(columnDefinition = "TEXT")
   private String imageUrl;

   @OneToMany(targetEntity = Comment.class, mappedBy= "recipe",
           fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   private List<Comment> commentList;

  @OneToMany(targetEntity = Product.class, mappedBy = "recipe",
          cascade = CascadeType.ALL)
   private List<Product> productList;

  @Column
  private long position;

  public Recipe() {
      this.dateAdded = LocalDate.now();
      this.commentList = new ArrayList<>();
      this.productList = new ArrayList<>();
      this.ratingList = new ArrayList<>();
  }

  public boolean hasImageUrl(){
      return this.imageUrl != null;
  }

  public void addRating(double currentRating) {
    this.ratingList.add(currentRating);
    this.rating = ratingList
            .stream()
            .mapToDouble(Double::doubleValue)
            .average().orElse(0);
//      double sum = 0;
//      for (Double rating : ratingList) {
//          sum += rating;
//          this.rating = sum / ratingList.size();
//      }

  }

}
