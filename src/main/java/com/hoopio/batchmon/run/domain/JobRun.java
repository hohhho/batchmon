package com.hoopio.batchmon.run.domain;

import com.hoopio.batchmon.job.domain.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "job_run")
@Entity
public class JobRun {

    @Id
    @SequenceGenerator(name = "seq_job_run", sequenceName = "seq_job_run", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_job_run")
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RunStatus status;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    private Long durationMs;

    private Integer exitCode;

    private String hostName;

    @Enumerated(EnumType.STRING)
    private TriggerType triggerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;


    // private constructor
    private JobRun(Job job, RunStatus status, LocalDateTime startedAt, String hostName, TriggerType triggerType) {
        this.job = job;         // job_id
        this.status = status;
        this.startedAt = startedAt;
        this.hostName = hostName;
        this.triggerType = triggerType;
    }

    // static factory method
    public static JobRun start(Job job, LocalDateTime startedAt, String hostName, TriggerType triggerType) {
        if(job.getId() == null){
            throw new IllegalArgumentException("Job id cannot be null");    // fixme: 커스텀 예외 만들면 교체
        }

        // job이 보낸 시작 시간이 null이면 서버 시각으로 대체
        if(startedAt == null){
            startedAt = LocalDateTime.now();
        }

        if(triggerType == null){
            triggerType = TriggerType.SCHEDULED;
        }

        return new JobRun(job, RunStatus.RUNNING, startedAt, hostName, triggerType);
    }
}
