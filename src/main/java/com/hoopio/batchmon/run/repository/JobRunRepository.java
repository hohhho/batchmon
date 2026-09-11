package com.hoopio.batchmon.run.repository;

import com.hoopio.batchmon.run.domain.JobRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRunRepository extends JpaRepository<JobRun, Long> {
}
