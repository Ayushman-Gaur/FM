package com.organizer.match.match_organizer.services;

import com.organizer.match.match_organizer.dtos.ParticipationDto;
import com.organizer.match.match_organizer.entity.Match;
import com.organizer.match.match_organizer.entity.Participation;
import com.organizer.match.match_organizer.entity.User;
import com.organizer.match.match_organizer.enums.Status;
import com.organizer.match.match_organizer.repository.MatchRepo;
import com.organizer.match.match_organizer.repository.ParticipationRepo;
import com.organizer.match.match_organizer.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParticipationService
{
    private UserRepo userRepo;
    private MatchRepo matchRepo;
    private ParticipationRepo  participationRepo;

    public ParticipationDto joinMatch(Long matchId,String username)
    {
        User user=  userRepo.findByUserName(username).orElseThrow(()->new EntityNotFoundException("User not found"));
        Match match= matchRepo.findById(matchId).orElseThrow(()-> new EntityNotFoundException("Match not found"));
        Participation savedParticipation = null;
        if(match.getStatus()== Status.OPEN || match.getMaxPlayer()<12)
        {
            Participation participation = new Participation();
            participation.setMatch(match);
            participation.setUser(user);
            participation.setConfirmed(true);
            savedParticipation=participationRepo.save(participation);
        }

        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setMatchId(matchId);
        participationDto.setUserId(user.getId());
        assert savedParticipation != null;
        participationDto.setConfirmed(savedParticipation.getConfirmed());
        return participationDto;
    }
}
