package com.hoopio.batchmon.run.controller;

import com.hoopio.batchmon.run.domain.JobRun;
import com.hoopio.batchmon.run.dto.StartRunRequest;
import com.hoopio.batchmon.run.dto.StartRunResponse;
import com.hoopio.batchmon.run.service.JobRunService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JobRunController {

    private final JobRunService jobRunService;

    @PostMapping("/jobs/{jobKey}/runs")
    public ResponseEntity<StartRunResponse> startJob(@RequestBody @Valid StartRunRequest startRunRequest, @PathVariable String jobKey) {
        // jobRun 저장, runId 반환
        Long runId = jobRunService.startJob(startRunRequest, jobKey);

        // response dto에 runId 담기
        StartRunResponse startRunResponse = new StartRunResponse(runId);

        return new ResponseEntity<>(startRunResponse, HttpStatus.CREATED);
    }
}
