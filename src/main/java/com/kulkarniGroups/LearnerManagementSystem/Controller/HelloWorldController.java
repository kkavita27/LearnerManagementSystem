package com.kulkarniGroups.LearnerManagementSystem.Controller;

import com.kulkarniGroups.LearnerManagementSystem.Entity.Cohort;
import com.kulkarniGroups.LearnerManagementSystem.Entity.Learner;
import com.kulkarniGroups.LearnerManagementSystem.Exception.LearnerNotFoundException;
import com.kulkarniGroups.LearnerManagementSystem.Service.CohortManagementService;
import com.kulkarniGroups.LearnerManagementSystem.Service.LeanerManagementService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloWorldController {
    /*
    This is a controller, this does not have responsibility to deal with database. Law of demeter principle,
    controller should only talk to service, service should be responsible for talking to db.
    Service contains business logic.
     */
    /*
		Dividing application into 3 layer
		Controller
		Service
		Repository

		Other than above Entities.
		 */

    @Autowired
    private LeanerManagementService learnerManagementService;
    @Autowired
    private CohortManagementService cohortManagementService;
    //Controller has a service - association - has -a


    @GetMapping("/")
    public String HelloWorld()
    {  //Simple end point
        return "Hello Kavita!";
    }

    @GetMapping("/hello")
    public String hello()
    {
        //This is a RESTful endpoint
        return "Hello world through RESTful endpoint";
    }

    /*
    You need @RequestBody mapping to do the conversion of data being sent, otherwise it won't be
    able to do conversion of java object, it will set null data to fields.
     */
    @PostMapping("/learners")
    public Learner createLearner(@Valid @RequestBody Learner learner)
    {
        return learnerManagementService.createLearner(learner);
    }
//You cant have two end points with same name and same http verbs
//    @GetMapping("/learners")
//     public List<Learner>  getAllLearners()
//    {
//        return learnerManagementService.getAllLearners();
//    }

//    @GetMapping("/learners/{learnerId}")
//    public Learner getLearner(@PathVariable("learnerId") long learnerId)
//    {
//        return learnerManagementService.findByLearnerId(learnerId);
//    }

//    @GetMapping("/learners")
//    public List<Learner> getLearner(@RequestParam(value="learnerId",required=false) Long learnerId)
//    {
//        if(learnerId==null)
//        {
//            return learnerManagementService.getAllLearners();
//        }
//         Learner learner = learnerManagementService.findByLearnerId(learnerId);
//         return List.of(learner);
//    }

//    @GetMapping("/learners/{learnerName}")
//    public Learner getLearnerByName(@PathVariable("learnerName") String learnerName)
//    {
//        return learnerManagementService.findByLearnerName(learnerName);
//    }

    /*
     if you want to pass id and name as param, either you can find by Id or by Name but can't find by both.
     if you removed required as false from anyone then you will have to pass it as parameter.
     */


    @GetMapping("/learners")
    public List<Learner> getLearner(
            @RequestParam(value="learnerId", required=false) Long learnerId,
            @RequestParam(value ="learnerName", required = false, defaultValue = "") String learnerName) throws LearnerNotFoundException
            /*
            defaultValue = "": This ensures learnerName is treated as an empty string ("") when not provided in the URL, avoiding potential null issues.
             */
    {
        if (learnerId == null && learnerName.isEmpty()) {
            return learnerManagementService.getAllLearners();
        }

        // If learnerId is provided, fetch by learnerId
        if (learnerId != null) {
            Learner learner = learnerManagementService.findByLearnerId(learnerId);
            return List.of(learner);
        }

        // If learnerName is provided and not empty, fetch by learnerName
        Learner learner = learnerManagementService.findByLearnerName(learnerName);
        return List.of(learner);
        // Fallback - return empty list if no conditions are met (shouldn't happen with above logic)
    }
       /*
       if you have to update something, you will need put
        */

    @PutMapping("/learners")
    public Learner updateLearner(@RequestBody Learner learner)
    {
        /* To update the existing learner, you take request body, whatever user sends you, you save it.
        whenever you want to update, you take the entire object and replace the entire object -

        Taking one query parameter say it could be name,id and say you want to update emailId is another option, but not best practice.
         */
        return learnerManagementService.updateLearner(learner);

    }
    //localised exception handler
    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity<String> handleLearnerNotFoundException(LearnerNotFoundException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        //HttpStatus.NOT_FOUND client error.
    }


    @PostMapping("/cohorts")
    public Cohort createCohort(@RequestBody Cohort cohort)
    {
        return cohortManagementService.createCohort(cohort);
    }


    @GetMapping("/cohorts")
    public List<Cohort> getCohorts(@RequestParam(value="cohortId", required=false) Long cohortId) throws LearnerNotFoundException {
        // If cohortId is provided, fetch by cohortId
        if (cohortId != null) {
            Cohort cohort = cohortManagementService.findByCohortId(cohortId);
            return List.of(cohort);
        }
        return cohortManagementService.getAllCohorts();
    }


    @PostMapping("/assignLearners")
    public Cohort assignLearnerToCohort(@RequestParam("learnerId") Long learnerId, @RequestParam("cohortId") Long cohortId)
    {
        return cohortManagementService.assignLearnerToCohort(learnerId,cohortId);
    }
}
