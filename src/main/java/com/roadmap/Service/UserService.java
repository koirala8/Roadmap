package com.roadmap.Service;

import com.roadmap.Dto.UserDto;


import java.util.List;

public interface UserService {

    void save(UserDto userDto);

    UserDto findByUsernameAndPassword(String username, String password, String role);

    List<UserDto> findAll();

    void deleteById(Long id);

    UserDto findById(Long id);

    void update(UserDto userDto);
}
