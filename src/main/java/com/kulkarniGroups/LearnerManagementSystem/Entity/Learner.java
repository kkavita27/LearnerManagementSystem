package com.kulkarniGroups.LearnerManagementSystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Entity
public class Learner {
    //ORM - Object relational mapping. Which does the translation of the object into db objects.
    //JPA uses hibernate internally.


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long learnerId;
    @NotNull
    @NotBlank (message="Naam likh!!!")
    private String learnerName;
    private String learnerPhone;
    @Email
    @NotNull(message = "Can not be null")
    private String learnerEmail;
    private String learnerAddress;

//    @ManyToMany(mappedBy = "learners")
//    public List<Cohort> cohorts;
    // By mapping  @ManyToMany(mappedBy = "learners") spring will not create another table. Two parties can not be owner, Cohort is owner.
    // No hard & fast rule to be owner, Learner can also be owner, but think logically before creating owner.

//    public List<Cohort> getCohorts() {
//        return cohorts;
//    }
//
//    public void setCohorts(List<Cohort> cohorts) {
//        this.cohorts = cohorts;
//    }

    public Learner()
    {

    }
    public Learner(long learnerId, String learnerName, String learnerPhone, String learnerEmail, String learnerAddress, List<Cohort> cohorts) {
        this.learnerId = learnerId;
        this.learnerName = learnerName;
        this.learnerPhone = learnerPhone;
        this.learnerEmail = learnerEmail;
        this.learnerAddress = learnerAddress;
//        this.cohorts = cohorts;
    }

    public long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(long learnerId) {
        this.learnerId = learnerId;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public String getLearnerPhone() {
        return learnerPhone;
    }

    public void setLearnerPhone(String learnerPhone) {
        this.learnerPhone = learnerPhone;
    }

    public String getLearnerEmail() {
        return learnerEmail;
    }

    public void setLearnerEmail(String learnerEmail) {
        this.learnerEmail = learnerEmail;
    }

    public String getLearnerAddress() {
        return learnerAddress;
    }

    public void setLearnerAddress(String learnerAddress) {
        this.learnerAddress = learnerAddress;
    }

}
