package com.example.person.handler;

import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionFilters {
    private String title;
    private Integer status;
    private String details;
    private LocalDateTime timeStamp;
}
