package com.organizer.match.match_organizer.controller;

import com.organizer.match.match_organizer.dtos.MatchDto;
import com.organizer.match.match_organizer.services.MatchService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController
{
    private final MatchService matchService;

    @PostMapping("/create")
    public ResponseEntity<MatchDto> createMatch(@RequestBody MatchDto matchDto)
    {
        if(matchDto.getDateTime().isBefore(LocalDateTime.now()))
        {
            return ResponseEntity.badRequest().build();
        }
        MatchDto createdMatchDto = matchService.createMatch(matchDto);
        return ResponseEntity.ok(createdMatchDto);
    }

    @GetMapping("/confirm")
    public int getConfirmedPlayer(@RequestParam Long matchId)
    {
        return matchService.getConfirmedPlayer(matchId);
    }
}
