package com.hoopio.batchmon.run.service;

import com.hoopio.batchmon.job.domain.Job;
import com.hoopio.batchmon.job.service.JobService;
import com.hoopio.batchmon.run.domain.JobRun;
import com.hoopio.batchmon.run.dto.StartRunRequest;
import com.hoopio.batchmon.run.repository.JobRunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobRunService {

    private final JobRunRepository jobRunRepository;
    private final JobService jobService;

    /**
     * 배치 잡이 시작할 때, jobRun에 데이터를 추가하고 runId를 반환
     * @param startRunRequest
     * @return runId
     */
    @Transactional
    public Long startJob(StartRunRequest startRunRequest, String jobKey) {
        // 해당 jobKey를 가진 job이 있는지 조회
        Job job = jobService.getJobByJobKey(jobKey);

        // 해당 job이 없다면 getJobByJobKey 안에서 예외 터지고, 있다면 jobRun 저장
        JobRun jobRun = JobRun.start(job,startRunRequest.startedAt(), startRunRequest.hostName(), startRunRequest.triggerType());
        jobRun = jobRunRepository.save(jobRun);

        // 저장한 jobRun의 id return
        return jobRun.getId();
    }
}
