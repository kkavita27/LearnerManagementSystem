package com.kulkarniGroups.LearnerManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cohort {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cohortId;

    private String cohortName;
    private String cohortDescription;

    //Cohort is composed of Learner and annotate the field with many to many relationship.



    @ManyToMany
    private List<Learner> learners;
    /*
    You can map entities which are tables, only if you specify list of learners it will create separate table.
    If you give learnerIds it won't create relationship. Relationship will come in picture only if entities are in picture.
     */


    public List<Learner> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }

    public Long getCohortId() {
        return cohortId;
    }

    public void setCohortId(Long cohortId) {
        this.cohortId = cohortId;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public String getCohortDescription() {
        return cohortDescription;
    }

    public void setCohortDescription(String cohortDescription) {
        this.cohortDescription = cohortDescription;
    }

    public Cohort() {

    }

    public Cohort(Long cohortId, String cohortName, String cohortDescription,List<Learner> learners)
    {
        this.cohortId = cohortId;
        this.cohortName = cohortName;
        this.cohortDescription = cohortDescription;
        this.learners = learners;
    }




}
