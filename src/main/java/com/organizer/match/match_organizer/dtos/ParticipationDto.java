package com.organizer.match.match_organizer.dtos;

import lombok.Data;

@Data
public class ParticipationDto
{
    private Long id;
    private Long matchId;
    private Long userId;
    private boolean confirmed;
}
