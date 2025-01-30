package com.kulkarniGroups.LearnerManagementSystem.Service;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.kulkarniGroups.LearnerManagementSystem.Entity.Learner;
import com.kulkarniGroups.LearnerManagementSystem.Exception.LearnerNotFoundException;
import com.kulkarniGroups.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeanerManagementService {
    /*
    Service has became a bean, can be injected at different places.
    Controller needs to talk to learner. Use association - has-a
     */

     //inject repo.
    @Autowired
    private LearnerRepository learnerRepository;

    public Learner createLearner(Learner learner)
    {
       return learnerRepository.save(learner);
    }

    public List<Learner> getAllLearners()
    {
        return learnerRepository.findAll();
    }

    public Learner findByLearnerId(long learnerId) throws LearnerNotFoundException{
        Optional<Learner> learner = learnerRepository.findById(learnerId);
        if(learner.isPresent())
        {
            return learner.get();
        }
        throw new LearnerNotFoundException("Learner not found, learnerId: " + learnerId);
//        try {
//            throw new LearnerNotFoundException("Learner not found with mention ID: " + learnerId);
//        } catch (LearnerNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }


    public Learner findByLearnerName(String learnerName) {
        return learnerRepository.findByLearnerName(learnerName);
    }

    public Learner updateLearner(Learner learner) {
        if (learnerRepository.findById(learner.getLearnerId()).isPresent()) {
            Learner existingLearner = learnerRepository.findById(learner.getLearnerId()).get();
            existingLearner.setLearnerName(learner.getLearnerName());
            existingLearner.setLearnerAddress(learner.getLearnerAddress());
            existingLearner.setLearnerEmail(learner.getLearnerEmail());
            existingLearner.setLearnerPhone(learner.getLearnerPhone());
//            learnerRepository.save(existingLearner);
        }
        return learnerRepository.save(learner);
    }
}
