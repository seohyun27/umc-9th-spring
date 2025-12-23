// MissionService.java
@Service
@Transactional
public class MissionService {

    private final StoreMissionRepository storeMissionRepository;
    private final UserMissionRepository userMissionRepository;

    public MissionService(StoreMissionRepository storeMissionRepository, UserMissionRepository userMissionRepository) {
        this.storeMissionRepository = storeMissionRepository;
        this.userMissionRepository = userMissionRepository;
    }

    public void startMissionChallenge(Long userId, Long missionId) {
        // 1. 가게 미션(StoreMission) 존재 여부 확인
        StoreMission mission = storeMissionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("미션을 찾을 수 없습니다."));

        // 2. 사용자가 이미 해당 미션에 도전 중인지 확인
        if (userMissionRepository.existsByUserIdAndMissionIdAndStatus(
                userId, missionId, UserMissionStatus.CHALLENGING)) {
            throw new DuplicateChallengeException("이미 도전 중인 미션입니다.");
        }

        // 3. 미션 도전(UserMission) 엔티티 생성 및 저장
        UserMission userMission = UserMission.builder()
                .userId(userId)
                .mission(mission)
                .status(UserMissionStatus.CHALLENGING) // 도전 중 상태로 설정
                .build();

        userMissionRepository.save(userMission);
    }
}
// UserMissionStatus는 ENUM으로 가정 (e.g., CHALLENGING, SUCCESS, FAILED)
