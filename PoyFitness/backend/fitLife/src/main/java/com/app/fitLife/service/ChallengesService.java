package com.app.fitLife.service;

import com.app.fitLife.model.Challenges;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ChallengesService {

    List<Challenges> getAll();
    Challenges getChallengeById(Long id);
    Challenges updateChallenge(Long id, Challenges challenges);
    void deleteChallenge(Long id);
    Challenges addUChallenge(Challenges challenges);
}
