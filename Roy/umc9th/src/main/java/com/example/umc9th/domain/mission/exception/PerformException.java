package com.example.umc9th.domain.mission.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class PerformException extends GeneralException {
    public PerformException(BaseErrorCode code) {
        super(code);
    }
}