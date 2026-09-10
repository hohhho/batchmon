package com.hoopio.batchmon.job.domain;

import com.hoopio.batchmon.common.config.JpaConfig;
import com.hoopio.batchmon.job.domain.Job;
import com.hoopio.batchmon.job.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig.class)
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    void createdAt이_자동으로_들어간다() {
        Job saved = jobRepository.save(Job.create("daily-sales", "일 매출 집계"));
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void 값을_바꾸면_update가_나간다(){
        Job saved = jobRepository.save(Job.create("daily-sales", "일 매출 집계"));
        saved.changeName("일 매출 집계 v2");
        jobRepository.flush();
    }

}
