# Jpa 작성하기 

1. 리뷰 작성하기
    - 해당 가게의 리뷰와 리뷰에 해당하는 사진들을 불러옵니다.
    
    ```java
        @Query("SELECT r FROM Review r " +
                "LEFT JOIN FETCH r.reviewPhotos " +
                "WHERE r.store.id = :storeId")
        List<Review> findReviewsWithPhotosByStoreId(@Param("storeId") Long storeId);
    ```
    
2. 마이페이지 조회
    - 내 정보를 모두 불러옵니다.
    
    ```java
        @Query("SELECT m FROM Member m WHERE m.id = :memberId")
        List<Member> findMemberById(@Param("memberId") Long memberId);
    ```
    
3. 진행중/완료 미션 조회
    - 수행 테이블에서 미션의 상태가 옵션(진행중 / 완료)에 따라 불러옵니다.
    
    ```java
        // 3번 진행중/완료 미션 조회
        @Query("SELECT p FROM Perform p " +
                "JOIN FETCH p.mission m " +
                "JOIN FETCH m.store s " +
                "WHERE p.member.id = :memberId " +
                "AND p.status = :status")
        List<Perform> findByMemberIdAndStatusWithMissionAndStore(
                @Param("memberId") Long memberId,
                @Param("status") boolean status
        );
    ```
    
4. 홈 화면 조회
    - 페이징의 개념에 따라 처음 조회, 그외 조회로 나뉩니다.
    
    ```java
        // 4번 홈 화면 - 도전 가능한 미션 조회 (첫 페이지)
        @Query("SELECT p FROM Perform p " +
                "JOIN FETCH p.mission m " +
                "JOIN FETCH m.store s " +
                "WHERE p.member.id = :memberId " +
                "AND p.status = false " +
                "AND s.region.unit = :regionName " +
                "ORDER BY m.endDate DESC, p.id DESC")
        List<Perform> findAvailableMissionsByRegionFirstPage(
                @Param("memberId") Long memberId,
                @Param("regionName") String regionName,
                Pageable pageable
        );
    
        // 4번 홈 화면 - 도전 가능한 미션 조회 (커서 페이징)
        @Query("SELECT p FROM Perform p " +
                "JOIN FETCH p.mission m " +
                "JOIN FETCH m.store s " +
                "WHERE p.member.id = :memberId " +
                "AND p.status = false " +
                "AND s.region.unit = :regionName " +
                "AND (m.endDate, p.id) < (:lastEndDate, :lastId) " +
                "ORDER BY m.endDate DESC, p.id DESC")
        List<Perform> findAvailableMissionsByRegionAfterCursor(
                @Param("memberId") Long memberId,
                @Param("regionName") String regionName,
                @Param("lastEndDate") LocalDateTime lastEndDate,
                @Param("lastId") Long lastId,
                Pageable pageable
        );
    ```
