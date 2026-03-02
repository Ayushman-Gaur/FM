package com.organizer.match.match_organizer.controller;

import com.organizer.match.match_organizer.dtos.ParticipationDto;
import com.organizer.match.match_organizer.repository.MatchRepo;
import com.organizer.match.match_organizer.repository.UserRepo;
import com.organizer.match.match_organizer.services.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class ParticipationController
{
    private final ParticipationService participationService;

    private final UserRepo userRepo;

    private final MatchRepo matchRepo;

    @PostMapping("/match")
    public ResponseEntity<ParticipationDto> joinMatch(@RequestParam Long matchId, @RequestParam String userName)
    {
        if(userRepo.findByUserName(userName).isPresent())
        {
            if(matchRepo.findById(matchId).isPresent())
            {
                return ResponseEntity.ok(participationService.joinMatch(matchId, userName));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
