package org.example.taskforpractive.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends DTO {
    private String username;
    private Boolean role;
    private String hashed_password;
}
