package com.organizer.match.match_organizer.entity;

import com.organizer.match.match_organizer.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Role Role;
    private String State;
    private String email;

    @OneToMany(mappedBy = "hostedBy")
    private List<Match> organizedMatches;

    @OneToMany
    private List<Participation> participationList;
}
