package com.kulkarniGroups.LearnerManagementSystem.Repository;

import com.kulkarniGroups.LearnerManagementSystem.Entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CohortRepository extends JpaRepository<Cohort,Long>
{

}
