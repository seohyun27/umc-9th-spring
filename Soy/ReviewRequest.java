// ReviewRequest.java
public class ReviewRequest {
    // 1~5점 사이의 평점
    @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다.")
    @Max(value = 5, message = "평점은 최대 5점 이하여야 합니다.")
    private Integer rating;

    @NotBlank(message = "리뷰 내용은 필수입니다.")
    @Size(max = 500, message = "리뷰 내용은 500자를 초과할 수 없습니다.")
    private String content;

    // getter, setter, constructor 생략
}
