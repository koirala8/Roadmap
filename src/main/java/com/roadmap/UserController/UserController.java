package com.roadmap.UserController;

import com.roadmap.Dto.LoginRequestDto;
import com.roadmap.Dto.UserDto;
import com.roadmap.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> showAll() {
        List<UserDto> listOfUser = userService.findAll();
        return new ResponseEntity<>(listOfUser, HttpStatus.OK); // 200
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequest) {
        UserDto userDto = userService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getRole());

        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> fetchUser(@PathVariable Long userId) {
        UserDto userDto = userService.findById(userId);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> doRegistration(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED); // 201
    }

    @PutMapping("/update")
    public ResponseEntity<String> doUpdate(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK); // 200
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteById(userId);
            return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK); // 200
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Record does not exist in database", HttpStatus.NOT_FOUND); // 404
        } catch (Exception e) {
            return new ResponseEntity<>("Problem in deletion", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}
