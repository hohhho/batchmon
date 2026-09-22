package com.hoopio.batchmon.job.exception;

import com.hoopio.batchmon.common.exception.BusinessException;
import com.hoopio.batchmon.common.exception.ErrorCode;

public class JobNotFoundException extends BusinessException {

    public JobNotFoundException(String jobKey) {
        super(ErrorCode.JOB_NOT_FOUND, jobKey);
    }
}
