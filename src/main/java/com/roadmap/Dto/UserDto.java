package com.roadmap.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String role;
}
