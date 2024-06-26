package org.example.taskforpractive.service.impl;

import org.example.taskforpractive.dto.NewsDTO;
import org.example.taskforpractive.dto.RequestDTO;
import org.example.taskforpractive.dto.UserDTO;
import org.example.taskforpractive.exceptions.NoSuchUserException;
import org.example.taskforpractive.exceptions.NotAdminException;
import org.example.taskforpractive.model.RequestModel;
import org.example.taskforpractive.model.UserModel;
import org.example.taskforpractive.repository.RequestRepository;
import org.example.taskforpractive.repository.UserRepository;
import org.example.taskforpractive.service.NewsService;
import org.example.taskforpractive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RequestRepository requestRepository;
    private NewsService newsService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RequestRepository requestRepository, NewsService newsService) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.newsService = newsService;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        UserModel user = new UserModel();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setRole(false);
        user.setCreated_at(LocalDateTime.now());
        user.setLinkAvatar("default_picture_img");
        user.setPassword(passwordEncoder.encode(userDTO.getHashed_password()));
        user.setRequests(new ArrayList<>());

        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws NoSuchUserException {
        List<UserModel> users = userRepository.findAll();
        for(UserModel user : users){
            if(passwordEncoder.matches(user.getPassword(), userDTO.getHashed_password())){
                return convertUserToDto(user);
            }
        }
        throw new NoSuchUserException("This user does not exist");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<UserModel> users = userRepository.findAll();

        for (UserModel user : users) {
            userDTOS.add(convertUserToDto(user));
        }
        return userDTOS;
    }

    @Override
    public UserDTO convertUserToDto(UserModel user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(userDto.getUsername());
        userDto.setRole(user.getRole());
        userDto.setCreated_at(user.getCreated_at());
        userDto.setHashed_password(user.getPassword());

        return userDto;
    }
    @Override
    public UserDTO getUserById(Long id) throws NoSuchUserException {
        UserModel user = userRepository.findById(id).orElse(null);
        if(user != null){
            return convertUserToDto(user);
        }else{
            throw new NoSuchUserException("This user does not exists");
        }
    }
    @Override
    public RequestDTO makeRequest(Long id, String text) throws NoSuchUserException{
        UserModel userModel = userRepository.findById(id).orElse(null);
        if(userModel != null){
            RequestModel request = new RequestModel();
            request.setUser(userModel);
            request.setText(text);
            request.setCreated_at(LocalDateTime.now());
            request.setSentBy(userModel.getUsername());

            return convertRequestToDto(request);
        }else{
            throw new NoSuchUserException("This user doesn't exists");
        }
    }

    @Override
    public NewsDTO publishNews(Long user_id, NewsDTO newsDTO) throws NotAdminException {
        UserModel userModel = userRepository.findById(user_id).orElse(null);
        if(userModel != null && userModel.getRole()){
            return newsService.publishNews(newsDTO.getHeading(), newsDTO.getContent());
        }else{
            throw new NotAdminException("You are not admin!");
        }
    }

    @Override
    public RequestDTO convertRequestToDto(RequestModel request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUserId(requestDTO.getUserId());
        requestDTO.setText(request.getText());
        requestDTO.setCreated_at(request.getCreated_at());
        requestDTO.setCreatedBy(request.getSentBy());
        return requestDTO;
    }

    public UserDTO deleteUser(Long id) throws NoSuchUserException {
        UserModel userToDelete = userRepository.findById(id).orElse(null);
        if(userToDelete != null){
            userRepository.delete(userToDelete);
            return convertUserToDto(userToDelete);
        }else{
            throw new NoSuchUserException("This user does not exist");
        }
    }


}
