# 7주차 미션 : api 응답 통일, 성공 (enum,class) 작성

## 1. 성공 메서드 작성하기
```java
@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseCode{
    SUCCESS_CODE(HttpStatus.ACCEPTED,"COMMON200","성공적으로 요청을 처리했습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
```

## 2. 리뷰 컨트롤러 응답 통일시키기

### 1) 일단 resDTO로 dto 묶기
```java
public class ReviewResDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewItemDTO {
        Long id;
        int rate;
        String content;
        String storeName;
    }
}
```

### 2) controller,repository 등에서 반환타입 변경하기
```java
    @GetMapping("reviews/search")
    public ApiResponse<List<ReviewResDto.ReviewItemDTO>> searchReview(@RequestParam String query, @RequestParam String type){
        List<ReviewResDto.ReviewItemDTO> reviewList = reviewQueryService.searchReview(query,type);
        GeneralSuccessCode code = GeneralSuccessCode.SUCCESS_CODE;
        return ApiResponse.onSuccess(code,reviewList);
    }
```

### 3) 에러 핸들러 추가해보기
   - params 가 없을 때도 커스텀 응답을 주고 싶어서 따로 핸들러에 추가함
   ```java
    // 파라미터 누락 예외
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> handleException(MissingServletRequestParameterException ex)
    {
        String missingParam = ex.getParameterName();
        String expectedType = ex.getParameterType();
        String errorMessage = String.format("파라미터 '%s'(%s 타입) 이 누락되었습니다.",missingParam,expectedType);

        // 코드 받기
        BaseCode code  = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code,errorMessage));
    }
  ```
## 4) 질문사항
   ### 1) BaseSuccessCode,BaseErrorCode로 인터페이스를 분리할 필요가 있을까?? 
    어차피 인터페이스는 구현하라고 있는 건데
    BaseCode 하나만 두고 Success, Error는 구현할 때 분리하면 되지 않을까 해서 제 코드에선 하나의 인터페이스만 정의함
   ### 2) converter가 왜 필요한가
      1) response DTO에서 왜 굳이 exception 클래스를 만들고 converter엔 왜 있지라는 궁금증 어차피 다 throw로 처리 할 꺼고 에러 핸들러에서 처리하니까 안 쓰이는 것 아닌가
      -> chatgpt에서도 굳이 필요없으니까 삭제하라고 함....
       2) 6주차에서 querydsl로 dto로 한번에 가져오라고 했는데 왜 converter가 필요한지... 이미 dto로 가져왔는데
   ### 3) 스터디 진행 시 궁금했던 getStatus 가 왜 쓰이는지 발견!! 에러 핸들러시 어떤 에러가 났는지를 발견하기 위해 사용 
   ### 4) 워크북에선 testString으로 처리했지만 review의 경우엔 dto의 list로 넘기는 거라서 고민
   ```java
    @Builder
   @Getter
   public static class ReviewItemDto {
   Long id;
   int rate;
   String content;
   String storeName;
   }
   ```
   로 만들어서 밖에서 List로 감싸느냐
   ```java
       @Getter
   @Builder
   public class ReviewListResponse {
   List<Review> reviews;
   }
   ```
   로 아예 DTO에서 LIST로 만드는지 고민했는데 item으로 작성함