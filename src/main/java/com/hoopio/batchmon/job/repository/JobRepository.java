package com.hoopio.batchmon.job.repository;

import com.hoopio.batchmon.job.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
