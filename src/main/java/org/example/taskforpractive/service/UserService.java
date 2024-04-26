package org.example.taskforpractive.service;

import org.example.taskforpractive.dto.NewsDTO;
import org.example.taskforpractive.dto.RequestDTO;
import org.example.taskforpractive.dto.UserDTO;
import org.example.taskforpractive.exceptions.NoSuchUserException;
import org.example.taskforpractive.exceptions.NotAdminException;
import org.example.taskforpractive.model.RequestModel;
import org.example.taskforpractive.model.UserModel;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO getUserById(Long userId) throws NoSuchUserException;
    List<UserDTO> getAllUsers();
    NewsDTO publishNews(Long id, NewsDTO newsDTO) throws NotAdminException;

    UserDTO convertUserToDto(UserModel user);
    UserDTO loginUser(UserDTO userDTO) throws NoSuchUserException;
    RequestDTO makeRequest(Long id, String text) throws NoSuchUserException;
    RequestDTO convertRequestToDto(RequestModel request);
}
