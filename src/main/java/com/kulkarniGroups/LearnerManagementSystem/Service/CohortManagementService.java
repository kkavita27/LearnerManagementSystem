package com.kulkarniGroups.LearnerManagementSystem.Service;

import com.kulkarniGroups.LearnerManagementSystem.Entity.Cohort;
import com.kulkarniGroups.LearnerManagementSystem.Entity.Learner;
import com.kulkarniGroups.LearnerManagementSystem.Exception.LearnerNotFoundException;
import com.kulkarniGroups.LearnerManagementSystem.Repository.CohortRepository;
import com.kulkarniGroups.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CohortManagementService {

    @Autowired
    private CohortRepository cohortRepository;

    @Autowired
    private LearnerRepository learnerRepository;



    public Cohort createCohort(Cohort cohort) {
        System.out.println("CohortManagementService createCohort" + cohort.toString());
        return cohortRepository.save(cohort);
    }


    public List<Cohort> getAllCohorts() {
        return  cohortRepository.findAll();
    }


    public Cohort findByCohortId(long cohortId) throws LearnerNotFoundException {
        Optional<Cohort> cohort = cohortRepository.findById(cohortId);
        if (cohort.isPresent()) {
            return cohort.get();
        }
        throw new LearnerNotFoundException("Learner not found, cohortId: " + cohortId);
    }

    public Cohort assignLearnerToCohort(Long learnerId, Long cohortId) {
        Learner learner = learnerRepository.findById(learnerId).get();
        Cohort cohort = cohortRepository.findById(cohortId).get();
        List<Learner> existingLearner = cohort.getLearners();
        existingLearner.add(learner);
        cohort.setLearners(existingLearner);
        return cohortRepository.save(cohort);
    }

}
