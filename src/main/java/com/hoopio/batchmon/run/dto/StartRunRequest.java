package com.hoopio.batchmon.run.dto;

import com.hoopio.batchmon.run.domain.TriggerType;

import java.time.LocalDateTime;

public record StartRunRequest(
        LocalDateTime startedAt,
        String hostName,
        TriggerType triggerType
) {
}
