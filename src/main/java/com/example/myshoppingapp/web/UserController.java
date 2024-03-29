package com.example.myshoppingapp.web;

import com.example.myshoppingapp.domain.users.*;
import com.example.myshoppingapp.service.PictureService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final PictureService pictureService;


    @Autowired
    public UserController(UserService userService, PictureService pictureService) {
        this.userService = userService;
        this.pictureService = pictureService;
    }




    @GetMapping("user/profile")
    public String ShowUserProfile(Model model){
        UserOutputDTO userOutputDTO = this.userService.getLoggedUserDTO();
        //TODO
//        String currentUrl = this.pictureService.getPictureUrlByLoggedUser();
//        model.addAttribute("pictureUrl", currentUrl);
<<<<<<< HEAD
        model.addAttribute("userEntity", userOutputDTO);
=======
        model.addAttribute("user", userOutputDTO);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        return "user/profile";
    }

    @GetMapping("/update-user")
    public String updateProduct(Model model) {
        UserOutputDTO currentUser = this.userService.getLoggedUserDTO();
        model.addAttribute("user", currentUser);
        return "user/update-user";
    }

    @PutMapping("/update-user")
    public String doUpdateProduct(Model model, UserInputDTO userInputDTO) {
        UserOutputDTO currentUser = this.userService.getLoggedUserDTO();
<<<<<<< HEAD
        model.addAttribute("userEntity", currentUser);
=======
        model.addAttribute("user", currentUser);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        userService.updateUser(userInputDTO);
        return "redirect:/user/profile";
    }

    @GetMapping("/delete-profile/{id}")
    public String deleteById(@PathVariable(value = "id") long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

}
