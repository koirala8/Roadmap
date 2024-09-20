package com.roadmap.Service;

import com.roadmap.Dto.UserDto;
import com.roadmap.Entity.User;
import com.roadmap.Repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserDto userDto) {
        User user = convertToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public UserDto findByUsernameAndPassword(String username, String password, String role) {
        User user = userRepository.findByUsernameAndPasswordAndRole(username, password, role);
        return user != null ? convertToDto(user) : null;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(this::convertToDto).orElse(null);
    }

    @Override
    public void update(UserDto userDto) {
        User user = convertToEntity(userDto);
        userRepository.save(user); // Save operation will update if the user entity contains an ID
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName()); // Ensure this line is correct
        user.setRole(userDto.getRole());
        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFullName()); // Ensure this line is correct
        userDto.setRole(user.getRole());
        return userDto;
    }

}