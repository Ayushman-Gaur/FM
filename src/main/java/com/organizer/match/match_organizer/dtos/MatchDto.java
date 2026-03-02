package com.organizer.match.match_organizer.dtos;

import com.organizer.match.match_organizer.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchDto
{
    private Long id;
    private LocalDateTime dateTime;
    private String location;
    private double latitude;
    private double longitude;
    private int maxPlayer;
    private Status status;
    private String organizerName;
}
