package com.app.fitLife.controller;

import com.app.fitLife.model.Challenges;
import com.app.fitLife.service.ChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("challenges")
@PreAuthorize("isAuthenticated()")
public class ChallengesController {

    @Autowired
    private ChallengesService challengesService;

    @GetMapping
    public ResponseEntity<List<Challenges>> getAll() {
        List<Challenges> challenges = challengesService.getAll();
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Challenges> getChallengesById(@PathVariable Long id) {
        Challenges challenge = challengesService.getChallengeById(id);
        return challenge != null ? ResponseEntity.ok(challenge) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Challenges> updateChallenge(@PathVariable Long id, @RequestBody Challenges challenges) {
        Challenges challengeExist = challengesService.getChallengeById(id);
        if (challengeExist == null) {
            return ResponseEntity.notFound().build();
        }

        challengeExist.setDuratiom(challenges.getDuratiom());
        challengeExist.setName(challenges.getName());
        challengeExist.setParcitipatingUserEntities(challenges.getParcitipatingUserEntities());

        Challenges updatedChallenge = challengesService.updateChallenge(id, challengeExist);
        return ResponseEntity.ok(updatedChallenge);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Challenges> addChallenge(@RequestBody Challenges challenges) {
        Challenges newChallenge = challengesService.addUChallenge(challenges);
        return ResponseEntity.ok(newChallenge);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        Challenges challenge = challengesService.getChallengeById(id);
        if (challenge == null) {
            return ResponseEntity.notFound().build();
        }
        challengesService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }
}
