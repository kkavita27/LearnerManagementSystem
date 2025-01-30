package com.kulkarniGroups.LearnerManagementSystem.Repository;

import com.kulkarniGroups.LearnerManagementSystem.Entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


    /*
    You should ideally create one repository for each table.

    Service talks to repository.

    JPARepo is by default available in spring boot. Which is providing facility of db operation.
     */

    @Repository
    public interface  LearnerRepository extends JpaRepository<Learner,Long>{

        //Learner findByName(String learnerName);


        //This is called as JPL query.
        @Query("SELECT l from Learner l where l.learnerName =:learnerName")
        Learner findByLearnerName(String learnerName);

    }




//}
