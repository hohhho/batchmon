package com.hoopio.batchmon.job.service;

import com.hoopio.batchmon.job.exception.JobNotFoundException;
import com.hoopio.batchmon.job.domain.Job;
import com.hoopio.batchmon.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    /**
     * jobKey로 Job 찾아서 반환
     * @param jobKey
     * @return
     */
    public Job getJobByJobKey(String jobKey) {
        return jobRepository.findByJobKey(jobKey)
                .orElseThrow(()-> new JobNotFoundException(jobKey));
    }
}
