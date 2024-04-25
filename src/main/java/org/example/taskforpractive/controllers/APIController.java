package org.example.taskforpractive.controllers;

import org.example.taskforpractive.dto.NewsDTO;
import org.example.taskforpractive.dto.RequestDTO;
import org.example.taskforpractive.dto.UserDTO;
import org.example.taskforpractive.exceptions.NoSuchUserException;
import org.example.taskforpractive.exceptions.NotAdminException;
import org.example.taskforpractive.service.NewsService;
import org.example.taskforpractive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class APIController {

    private UserService userService;
    private NewsService newsService;

    @Autowired
    public APIController(UserService userService, NewsService newsService) {
        this.userService = userService;
        this.newsService = newsService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO loggedInUser = userService.loginUser(userDTO);
            return ResponseEntity.ok(loggedInUser);
        } catch (NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/request")
    public ResponseEntity<RequestDTO> submitRequest(@RequestBody RequestDTO requestDTO) {
        try {
            RequestDTO submittedRequest = userService.makeRequest(requestDTO.getUserId(), requestDTO.getText());
            return ResponseEntity.status(HttpStatus.CREATED).body(submittedRequest);
        } catch (NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/news")
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

    @PostMapping("/news/publish")
    public ResponseEntity<NewsDTO> publishNews(@RequestParam Long user_id, @RequestBody NewsDTO newsDTO) {
        try {
            NewsDTO publishedNews = userService.publishNews(user_id, newsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(publishedNews);
        } catch (NotAdminException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
