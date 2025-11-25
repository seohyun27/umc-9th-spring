# SuccessEnum

package com.example.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // 200 OK
    OK(HttpStatus.OK, "요청이 성공적으로 처리되었습니다."),
    GET_SUCCESS(HttpStatus.OK, "조회에 성공했습니다."),
    
    // 201 Created
    CREATE_SUCCESS(HttpStatus.CREATED, "리소스를 성공적으로 생성했습니다."),
    UPLOAD_SUCCESS(HttpStatus.CREATED, "파일 업로드를 성공했습니다."),

    // 204 No Content
    DELETE_SUCCESS(HttpStatus.NO_CONTENT, "리소스를 성공적으로 삭제했습니다."),
    UPDATE_SUCCESS(HttpStatus.NO_CONTENT, "리소스를 성공적으로 변경했습니다.");

    // HTTP 상태 코드는 Enum 타입으로 직접 정의하여 일관성을 확보합니다.
    private final HttpStatus httpStatus;
    // 사용자 친화적인 메시지 (개발/디버깅 용도)
    private final String message;
}

# SuccessResponseDTO

package com.example.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Null 값은 JSON 출력에서 제외
public class CommonResponse<T> {

    // API 응답의 HTTP 상태 코드 (클라이언트에게 전송되는 실제 상태 코드와 다를 수 있음)
    private final int status;
    // 성공 응답 메시지
    private final String message;
    // 실제 응답 데이터 (Generic Type T)
    private final T data;

    // 1. 데이터가 포함된 성공 응답 생성자
    private CommonResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    // 2. 데이터가 없는 성공 응답 생성자 (주로 204 No Content에 사용)
    private CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null; 
    }

    // 팩토리 메서드: 데이터가 있는 경우
    public static <T> CommonResponse<T> of(SuccessCode code, T data) {
        return new CommonResponse<>(code.getHttpStatus().value(), code.getMessage(), data);
    }

    // 팩토리 메서드: 데이터가 없는 경우
    public static <T> CommonResponse<T> of(SuccessCode code) {
        return new CommonResponse<>(code.getHttpStatus().value(), code.getMessage());
    }
}

# SucessControllerMethod

package com.example.api.controller;

import com.example.common.response.SuccessCode;
import com.example.common.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class ExampleController {

    // 데이터 조회 (200 OK)
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<PostDto>> getPost(@PathVariable Long id) {
        // ... (서비스 로직)
        PostDto post = new PostDto(id, "예시 게시글입니다.");

        // SuccessCode.GET_SUCCESS (200 OK) 사용
        return ResponseEntity
                .status(SuccessCode.GET_SUCCESS.getHttpStatus())
                .body(CommonResponse.of(SuccessCode.GET_SUCCESS, post));
    }

    // 리소스 생성 (201 Created)
    @PostMapping
    public ResponseEntity<CommonResponse<PostDto>> createPost(@RequestBody PostCreateDto request) {
        // ... (서비스 로직: 리소스 생성 후 ID 100 반환 가정)
        PostDto createdPost = new PostDto(100L, request.getTitle());

        // SuccessCode.CREATE_SUCCESS (201 Created) 사용
        return ResponseEntity
                .status(SuccessCode.CREATE_SUCCESS.getHttpStatus())
                .body(CommonResponse.of(SuccessCode.CREATE_SUCCESS, createdPost));
    }

    // 리소스 삭제 (204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deletePost(@PathVariable Long id) {
        // ... (서비스 로직: 리소스 삭제)

        // SuccessCode.DELETE_SUCCESS (204 No Content) 사용
        // 204는 본문(Body)을 포함하지 않으므로, CommonResponse.of(SuccessCode) 사용
        return ResponseEntity
                .status(SuccessCode.DELETE_SUCCESS.getHttpStatus())
                .body(CommonResponse.of(SuccessCode.DELETE_SUCCESS));
    }
}
