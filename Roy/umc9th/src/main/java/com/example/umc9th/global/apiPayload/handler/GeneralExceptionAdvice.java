package com.example.umc9th.global.apiPayload.handler;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 애플리케이션에서 발생하는 커스텀 예외를 처리(런타임)
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(GeneralException ex)
    {
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(ex.getCode(),null));
    }
    // 파라미터 누락 예외
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> handleException(MissingServletRequestParameterException ex)
    {
        String missingParam = ex.getParameterName();
        String expectedType = ex.getParameterType();
        String errorMessage = String.format("파라미터 '%s'(%s 타입) 이 누락되었습니다.",missingParam,expectedType);

        // 코드 받기
        BaseErrorCode code  = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code,errorMessage));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex)
    {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code,ex.getMessage()));
    }
    // 컨트롤러 메서드에서 @Valid 어노테이션을 사용하여 DTO의 유효성 검사를 수행
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 Map
        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put(error.getObjectName(), error.getDefaultMessage())
        );
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        GeneralErrorCode code = GeneralErrorCode.VALID_FAIL;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        // 에러 코드, 메시지와 함께 errors를 반환
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }
}
