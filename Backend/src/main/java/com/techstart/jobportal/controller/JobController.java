package com.techstart.jobportal.controller;

import com.techstart.jobportal.model.Comment;
import com.techstart.jobportal.model.Job;
import com.techstart.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Allow all origins
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/alljobs")
    public List<Job> initializeJobs() {
        return jobService.checkAndInsertDefaultJobs();
    }

    @PutMapping("/jobs/{jobId}/like")
    public Job likeJob(@PathVariable Long jobId, @RequestParam boolean like) {
        Job job = jobService.findById(jobId);
        if (job != null) {
            if (like) {
                job.setLikeCount(job.getLikeCount() + 1); // Increment like count
            } else {
                job.setLikeCount(job.getLikeCount() - 1); // Decrement like count
            }
            jobService.save(job); // Save the updated job with new like count
        }
        return job; // Return the updated job
    }



    // In CommentController.java, update the endpoint:
    @PostMapping("/jobs/{jobId}/comment")
    public ResponseEntity<Job> addComment(@PathVariable Long jobId, @RequestBody Comment comment) {
        try {
            Job job = jobService.findById(jobId);
            if (job == null) {
                return ResponseEntity.notFound().build();
            }

            // Ensure comment has required fields
            if (comment.getText() == null || comment.getAuthor() == null) {
                return ResponseEntity.badRequest().build();
            }

            // Add the new comment
            if (job.getComments() == null) {
                job.setComments(new ArrayList<>());
            }
            job.getComments().add(comment);

            // Save and return
            Job updatedJob = jobService.save(job);
            return ResponseEntity.ok(updatedJob);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
