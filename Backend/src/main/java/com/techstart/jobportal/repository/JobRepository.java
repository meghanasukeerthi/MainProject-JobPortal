package com.techstart.jobportal.repository;

import com.techstart.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
