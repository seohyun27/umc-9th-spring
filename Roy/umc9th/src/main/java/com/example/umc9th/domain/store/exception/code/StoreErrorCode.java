package com.example.umc9th.domain.store.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"STORE401_1","해당 가게에 대해 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,"STORE404_1","가게가 존재하지 않습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
