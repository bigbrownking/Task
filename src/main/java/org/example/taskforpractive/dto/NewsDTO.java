package org.example.taskforpractive.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewsDTO extends DTO{
    private String heading;
    private String content;
}
