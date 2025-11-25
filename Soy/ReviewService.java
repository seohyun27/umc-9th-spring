// ReviewService.java
@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public ReviewService(ReviewRepository reviewRepository, StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.storeRepository = storeRepository;
    }

    public void addReview(Long userId, Long storeId, ReviewRequest request) {
        // 1. 가게(Store) 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("가게를 찾을 수 없습니다."));

        // 2. 리뷰 엔티티 생성
        Review review = Review.builder()
                .userId(userId)
                .store(store)
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        // 3. 리뷰 저장
        reviewRepository.save(review);

        // 4. (선택적) 가게의 평균 평점 업데이트 로직 등을 추가할 수 있습니다.
        // store.updateAverageRating(request.getRating());
    }
}
