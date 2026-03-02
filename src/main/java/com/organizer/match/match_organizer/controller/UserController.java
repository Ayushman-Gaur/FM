package com.organizer.match.match_organizer.controller;

import com.organizer.match.match_organizer.dtos.UserDto;
import com.organizer.match.match_organizer.entity.User;
import com.organizer.match.match_organizer.repository.UserRepo;
import com.organizer.match.match_organizer.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/a1/v1")
@AllArgsConstructor
public class UserController
{
    private final UserService userService;
    private final UserRepo userRepo;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        String name=userDto.getUserName();
        if(name!=null && !name.isEmpty() && userRepo.findByUserName(name).isPresent())
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/name")
    public ResponseEntity<UserDto> findUserByName(@RequestParam String userName)
    {
        if(userName==null || userName.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        Optional<User> savedUser = userRepo.findByUserName(userName);
        return ResponseEntity.ok(convertEntityToDto(savedUser.get()));
    }

    public UserDto convertEntityToDto(User newUser)
    {
        UserDto userDto = new UserDto();
        userDto.setUserName(newUser.getUserName());
        userDto.setEmail(newUser.getEmail());
        userDto.setRole(newUser.getRole());
        userDto.setState(newUser.getState());
        return userDto;
    }
}
