// UserMissionRepository.java
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    // 사용자가 특정 미션에 도전 중인지 확인하는 쿼리
    boolean existsByUserIdAndMissionIdAndStatus(Long userId, Long missionId, UserMissionStatus status);
}
