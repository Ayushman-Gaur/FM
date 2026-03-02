package com.organizer.match.match_organizer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "participation")
public class Participation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    private Boolean confirmed;

}
