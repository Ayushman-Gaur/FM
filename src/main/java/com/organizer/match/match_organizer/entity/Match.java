package com.organizer.match.match_organizer.entity;

import com.organizer.match.match_organizer.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "match")
public class Match
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User hostedBy;

    private LocalDateTime matchDate;

    private int maxPlayer;
    private String address;
    private double latitude;
    private double longitude;
    @Enumerated(EnumType.STRING)
    private Status status=Status.OPEN;

    @OneToMany(mappedBy = "match",fetch = FetchType.EAGER)
    private List<Participation> participatants;
}
