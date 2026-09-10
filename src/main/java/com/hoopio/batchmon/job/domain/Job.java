package com.hoopio.batchmon.job.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "job")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Job {

    @Id
    @SequenceGenerator(name = "seq_job", sequenceName = "seq_job",  allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_job")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String jobKey;

    @Column(nullable = false)
    private String name;

    private String owner;

    private Integer expectedDurationSec;

    private boolean alertEnabled;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    // private 생성자
    private Job(String jobKey, String name) {
        this.jobKey = jobKey;
        this.name = name;
    }

    // 정적 팩토리 메서드
    public static Job create(String jobKey, String name) {
        return new Job(jobKey, name);
    }

    // Test 이름 변경 메서드
    public void changeName(String newName) {
        this.name = newName;
    }

}
