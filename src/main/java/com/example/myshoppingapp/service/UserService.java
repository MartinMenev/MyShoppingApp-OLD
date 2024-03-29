package com.example.myshoppingapp.service;

import com.example.myshoppingapp.domain.beans.LoggedUser;
<<<<<<< HEAD
=======
import com.example.myshoppingapp.domain.enums.UserRole;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import com.example.myshoppingapp.domain.products.Product;
import com.example.myshoppingapp.domain.recipes.Recipe;
import com.example.myshoppingapp.domain.users.*;
import com.example.myshoppingapp.repository.ProductRepository;
import com.example.myshoppingapp.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Getter
@Setter
@Service
public class UserService {


    private UserRepository userRepository;
    private ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper, LoggedUser loggedUser
                      ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }




<<<<<<< HEAD
    public UserEntity findByUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username).orElseThrow(NoSuchElementException::new);
=======
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
    }

    public Long getLoggedUserId() {
        return this.loggedUser.getId();
    }

    public UserOutputDTO getLoggedUserDTO() {
<<<<<<< HEAD
        UserEntity userEntity = this.userRepository.findUserEntityByUsername(this.loggedUser.getUsername()).orElseThrow(NoSuchElementException::new);
        return this.modelMapper.map(userEntity, UserOutputDTO.class);
    }

    public void updateUser(UserInputDTO userInputDTO) {
        UserEntity user = this.getLoggedUser();

        if (!userInputDTO.getUsername().equals(this.loggedUser.getUsername())) {
            this.loggedUser.setUsername(userInputDTO.getUsername());

            user.setPassword(userInputDTO.getPassword())
                    .setUsername(userInputDTO.getUsername())
                    .setEmail(userInputDTO.getEmail());
        }
        this.userRepository.saveAndFlush(user);
=======
        User user = this.userRepository.findByUsername(this.loggedUser.getUsername()).orElseThrow(NoSuchElementException::new);
        return this.modelMapper.map(user, UserOutputDTO.class);
    }

    public void updateUser(UserInputDTO userInputDTO) {
        Long idToUpdate = this.getLoggedUserId();
        String newUsername = userInputDTO.getUsername();
        String newPassword = userInputDTO.getPassword();
        String newEmail = userInputDTO.getEmail();
        if (!newUsername.equals(this.loggedUser.getUsername())) {
            this.loggedUser.setUsername(newUsername);
        }
        this.userRepository.updateUser(idToUpdate, newUsername, newPassword, newEmail);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

    }

    //delete user, but first delete all his product lists (if any)!
    public void deleteUserById(long id) {
<<<<<<< HEAD
        Optional<List<Product>> userProducts= this.productRepository.findAllByUserEntityId(id);
=======
        Optional<List<Product>> userProducts= this.productRepository.findAllByUserId(id);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        if (userProducts.isPresent()) {
            for (Product userProduct : userProducts.get()) {
               this.productRepository.deleteById(userProduct.getId());
            }
        }
        this.userRepository.deleteById(id);
    }

    public void addBoughtProductToUser(Product product) {
<<<<<<< HEAD
        UserEntity userEntity = getLoggedUser();
        userEntity.getBoughtProducts().add(product);
        this.userRepository.saveAndFlush(userEntity);
    }

    public UserEntity getLoggedUser (){
=======
        User user = getLoggedUser();
        user.getBoughtProducts().add(product);
        this.userRepository.saveAndFlush(user);
    }

    public User getLoggedUser (){
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        return this.userRepository.findById(this.loggedUser.getId()).orElseThrow(NoSuchElementException::new);
    }




    public List<Recipe> getLoggedUserFavoriteList () {
       return this.getLoggedUser()
               .getFavoriteRecipes();
    }

    @Transactional
    @Modifying
    public void addRecipeToFavoriteList(Recipe recipe) {
<<<<<<< HEAD
        UserEntity userEntity = this.getLoggedUser();
        userEntity.getFavoriteRecipes().add(recipe);
        this.userRepository.saveAndFlush(userEntity);
=======
        User user = this.getLoggedUser();
        user.getFavoriteRecipes().add(recipe);
        this.userRepository.saveAndFlush(user);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
    }
}


