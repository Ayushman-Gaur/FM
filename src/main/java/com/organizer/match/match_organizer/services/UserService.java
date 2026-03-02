package com.organizer.match.match_organizer.services;

import com.organizer.match.match_organizer.dtos.UserDto;
import com.organizer.match.match_organizer.entity.User;
import com.organizer.match.match_organizer.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService
{
    private UserRepo userRepo;

    public UserDto createUser(UserDto userDto)
    {
        User savedUser = userRepo.save(convertDtoToEntity(userDto));
        return convertEntityToDto(savedUser);

    }

    public UserDto findByName(String userName)
    {
        if(userRepo.findByUserName(userName).isPresent())
        {
            return convertEntityToDto(userRepo.findByUserName(userName).get());
        }
        else
        {
            return null;
        }
    }

    public User convertDtoToEntity(UserDto userDto)
    {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setState(userDto.getState());
        return user;
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
