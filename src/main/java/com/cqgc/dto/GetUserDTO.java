package com.cqgc.dto;

import lombok.Data;

@Data
public class GetUserDTO {
    private Long id;
    private String username;
    private Integer role;
}
