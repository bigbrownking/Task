package org.example.taskforpractive.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class DTO {
    private Long id;
    private LocalDateTime created_at;
}
