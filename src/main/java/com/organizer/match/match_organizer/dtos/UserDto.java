package com.organizer.match.match_organizer.dtos;

import com.organizer.match.match_organizer.enums.Role;
import lombok.Data;

@Data
public class UserDto
{
    private Long id;
    private String userName;
    private String email;
    private Role role;
    private String state;
}
