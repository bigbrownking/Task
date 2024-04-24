package org.example.taskforpractive.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestDTO extends DTO{
    private String createdBy;
    private String text;
    private Long userId;
}
