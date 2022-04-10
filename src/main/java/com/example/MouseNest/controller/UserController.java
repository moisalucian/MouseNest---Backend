package com.example.MouseNest.controller;

import com.example.MouseNest.controller.dto.GenericResponseDto;
import com.example.MouseNest.controller.dto.UserLoginDto;
import com.example.MouseNest.controller.dto.UserRegistrationDto;
import com.example.MouseNest.model.Product;
import com.example.MouseNest.model.Role;
import com.example.MouseNest.model.User;
import com.example.MouseNest.service.ProductServiceImpl;
import com.example.MouseNest.service.UserService;
import com.example.MouseNest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/registration")//url-ul este luat de la th:action din html-ul corespunzator
//    public String register(@Valid @ModelAttribute(name = "user") UserRegistrationDto userRegistrationDto, BindingResult result) {
//        if (result.hasErrors()) {
//            return "/register";
//        }
//        User user = userService.findByEmail(userRegistrationDto.getEmail());
//        if (user != null) {
//            result.rejectValue("Email", null, "Email exists!");
//            return "/register";
//        }
//        userService.saveUser(userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), Role.CUSTOMER);
//        return "redirect:/registration?success";
//        //"?success" deoarece avem o conditie in html care verifica daca operataia a fost efectuat acu succes
//    }

    @PostMapping("/create-admin")//url-ul este luat de la th:action din html-ul corespunzator
    public String createAdmin(@Valid @ModelAttribute(name = "user") UserRegistrationDto userRegistrationDto, BindingResult result) {
        if (result.hasErrors()) {
            return "create-admin";//numele fisierului html
        }
        User user = userService.findByEmail(userRegistrationDto.getEmail());
        if (user != null) {
            result.rejectValue("Email", null, "Email exists!");
            return "create-admin";
        }
        userService.saveUser(userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), Role.ADMIN);
        return "redirect:/home-admin"; //redirect nu incarca pagina
        //la redirect trebuie sa pun "/"
    }

//    @GetMapping("/registration")//intre ghilimele trebuie sa specificam
//    //valoarea parametrului lui th:action (="${registration}") din html-ul corespunzator
//    public String registration(Model model) {
//        model.addAttribute("user", new UserRegistrationDto());//intre ghilimele trebuie sa specificam
//        //valoarea parametrului lui th:object (="${user}") din html-ul corespunzator
//        return "register";
//    }



    @PostMapping("/login")
    public ResponseEntity<GenericResponseDto> login(@RequestBody UserLoginDto userLoginDto) {
        boolean loggedIn = userServiceImpl.checkLogin(userLoginDto.getEmail(),userLoginDto.getPassword());
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setStatus(loggedIn);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        User user = userService.findByEmail(userRegistrationDto.getEmail());
        if (user != null) {
            genericResponseDto.setStatus(false);
//            result.rejectValue("Email", "409", "Email exists!");
            return new ResponseEntity<>(genericResponseDto, HttpStatus.CONFLICT);
        }
        userService.saveUser(userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), Role.CUSTOMER);
        genericResponseDto.setStatus(true);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.CREATED);
    }

}

