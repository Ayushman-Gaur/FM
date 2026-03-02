package com.organizer.match.match_organizer.services;


import com.organizer.match.match_organizer.dtos.MatchDto;
import com.organizer.match.match_organizer.entity.Match;
import com.organizer.match.match_organizer.entity.Participation;
import com.organizer.match.match_organizer.entity.User;
import com.organizer.match.match_organizer.repository.MatchRepo;
import com.organizer.match.match_organizer.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MatchService
{
    private final UserRepo userRepo;

    private final MatchRepo matchRepo;

    public MatchDto createMatch(MatchDto matchDto)
    {
        Match savedMatch;
        savedMatch= matchRepo.save(convertDtoToEntity(matchDto));
        return convertEntityToDto(savedMatch);
    }

    public int getConfirmedPlayer(Long matchId)
    {
        Match match= matchRepo.findById(matchId).orElseThrow(()-> new EntityNotFoundException("Match not found"));
        return (int)match.getParticipatants().stream().filter(Participation::getConfirmed).count();
    }


    public MatchDto convertEntityToDto(Match match)
    {
        MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setDateTime(match.getMatchDate());
        matchDto.setMaxPlayer(match.getMaxPlayer());
        matchDto.setStatus(match.getStatus());
        matchDto.setLatitude(match.getLatitude());
        matchDto.setLongitude(match.getLongitude());
        matchDto.setLocation(match.getAddress());
        if(userRepo.findByUserName(match.getHostedBy().getUserName()).isPresent())
        {
            matchDto.setOrganizerName(match.getHostedBy().getUserName());
        }
        return matchDto;
    }

    public Match convertDtoToEntity(MatchDto matchDto)
    {
        Match match = new Match();
        match.setId(matchDto.getId());
        match.setMatchDate(matchDto.getDateTime());
        match.setMaxPlayer(matchDto.getMaxPlayer());
        match.setStatus(matchDto.getStatus());
        match.setLongitude(matchDto.getLongitude());
        match.setLatitude(matchDto.getLatitude());
        match.setAddress(matchDto.getLocation());
        User user = userRepo.findByUserName(matchDto.getOrganizerName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        match.setHostedBy(user);
        return match;
    }
}
