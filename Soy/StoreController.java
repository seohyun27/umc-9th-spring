// StoreController.java
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final ReviewService reviewService;

    // 생성자 주입
    public StoreController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * POST /api/stores/{storeId}/reviews
     * 특정 가게에 리뷰를 추가합니다.
     */
    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<String> addReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewRequest request) { // @Valid로 요청 객체 유효성 검사

        // (실제 구현에서는 인증 정보를 통해 현재 사용자 ID를 가져와야 합니다.)
        Long userId = 1L; // 예시로 임의의 사용자 ID 사용

        reviewService.addReview(userId, storeId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body("리뷰가 성공적으로 등록되었습니다.");
    }
}
