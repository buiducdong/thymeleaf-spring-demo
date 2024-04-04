package com.example.springthymeleaf.controllers;

import com.example.springthymeleaf.bean.jpa.UserEntity;
import com.example.springthymeleaf.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String homePage(Model model) {
        return login(model);
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@Validated @ModelAttribute("userEntity") UserEntity userEntity,
                              BindingResult result,
                              Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("userEntity", userEntity);
                return "login";
            }

            String email = userEntity.getEmail();
            String password = userEntity.getPassword();

            Optional<UserEntity> userOpt = userService.findOneByEmail(email);

            if (userOpt.isEmpty() || !Objects.equals(password, userOpt.get().getPassword())) {
                model.addAttribute("userEntity", userEntity);
                model.addAttribute("message", "The email address or password is incorrect.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("message", "An error has occurred on the server.");
        }

        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        try {
            return pageSearch("",1, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/home-page")
    public String pageSearch(@RequestParam("keyWord") String keyWord,
                             @RequestParam("page") int currentPage,
                             Model model) {
        try {
            Pageable pageable = PageRequest.of(currentPage - 1, 5);

            Page<UserEntity> users = userService.getPageByNameLike(keyWord, pageable);
            model.addAttribute("users", users.getContent());
            model.addAttribute("size", users.getSize());
            model.addAttribute("totalRecords", users.getTotalElements());
            model.addAttribute("totalPages", users.getTotalPages());
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("keyWord", keyWord);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "home";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
