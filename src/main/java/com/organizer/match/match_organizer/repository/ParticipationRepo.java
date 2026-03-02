package com.organizer.match.match_organizer.repository;

import com.organizer.match.match_organizer.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepo extends JpaRepository<Participation,Long>
{
}
