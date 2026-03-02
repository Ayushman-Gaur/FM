package com.organizer.match.match_organizer.repository;

import com.organizer.match.match_organizer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
    Optional<User> findByUserName(String userName);
}
