package com.app.fitLife.service.impl;

import com.app.fitLife.model.Challenges;
import com.app.fitLife.repository.ChallengesRepository;
import com.app.fitLife.service.ChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengesServiceImpl implements ChallengesService {

    @Autowired
    private ChallengesRepository challengesRepository;

    @Override
    public List<Challenges> getAll() {
        return challengesRepository.findAll();
    }

    @Override
    public Challenges getChallengeById(Long id) {
        return challengesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found with ID:" + id));
    }

    @Override
    public Challenges updateChallenge(Long id, Challenges challenges) {
        Challenges challengeExist = challengesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found with ID:" + id));

        challengeExist.setName(challenges.getName());
        challengeExist.setDuratiom(challenges.getDuratiom());
        challengeExist.setParcitipatingUserEntities(challenges.getParcitipatingUserEntities());

        return challengesRepository.save(challengeExist);
    }

    @Override
    public void deleteChallenge(Long id) {
        challengesRepository.deleteById(id);
    }

    @Override
    public Challenges addUChallenge(Challenges challenges) {
        return challengesRepository.save(challenges);
    }
}
