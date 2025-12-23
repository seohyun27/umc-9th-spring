// MissionController.java
@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    /**
     * POST /api/missions/{missionId}/challenge
     * 특정 미션에 도전을 시작합니다.
     */
    @PostMapping("/{missionId}/challenge")
    public ResponseEntity<String> challengeMission(@PathVariable Long missionId) {

        // (실제 구현에서는 인증 정보를 통해 현재 사용자 ID를 가져와야 합니다.)
        Long userId = 1L; // 예시로 임의의 사용자 ID 사용

        missionService.startMissionChallenge(userId, missionId);

        return ResponseEntity.ok("미션 도전이 시작되었습니다.");
    }
}
