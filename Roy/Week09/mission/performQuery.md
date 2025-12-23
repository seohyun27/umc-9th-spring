## 진행중인 미션 조회하기

### 1. req
page, memberId, status
- 워크북에는 진행중인 미션만 하라고 되어 있는데 각각 가져올 경우를 대비해 0이면 진행 중, 1이면 완료, 2이면 모두
### 2. res
```json
{
  "페이지 번호",
  "미션 개수",
  "미션 리스트"
}
```
- res는 가게 미션 목록 가져오기 때 썻던 dto 그대로 쓴다
```java
    public ApiResponse<MissionResDTO.previewListDTO> getMissionsByStore(@Valid @ParameterObject PerformReqDTO.previewListDTO dto, @PageableDefault(size = 10) Pageable pageable)
    {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, performService.findMyMissions(dto,pageable));
    }
```
```java
    public static MissionResDTO.previewListDTO toPreviewList(Page<Perform> result)
    {
        return MissionResDTO.previewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(Perform::getMission)
                        .map(MissionConverter::toMissionItem)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
```
- 이렇게 mission에 있는 dto들을 그대로 내보내고 서비스만 다르게 구현
### 3. validate
```json
{
  "memberId": @ExistMemberId
}
```
### 4. 트러블 슈팅
- 진행중인 미션만 조회하진 않을 것 같아서 enum 클래스 사용해서 진행중 / 완료 구분
```java
    @EntityGraph
    Page<Perform> findAllByMemberId(Long memberId, Pageable pageable);
    @EntityGraph
    Page<Perform> findAllByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);
```
### 5. 동작 확인(빠르게 확인하기 위해 .http 사용)
##### 1) 일반 valid check : 모두 null이거나 blank
###### -> 기대: storeId 에러 표기해야 함
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "storeId": "가게가 존재하지 않습니다."
  }
}
```
##### 5) 미션 조회
###### -> 기대 : 200 ok , DB 정상 반영
###### -> 실제 응답
###### 모두
```json
{
  "isSuccess": true,
  "code": "MISSION302_1",
  "message": "미션을 조회하였습니다.",
  "result": {
    "missionList": [
      {
        "missionId": 1,
        "endDate": "2025-12-20T14:30:00",
        "point": 6000,
        "standardAmount": 20000,
        "storeName": "김밥천국"
      },
      {
        "missionId": 2,
        "endDate": "2025-12-20T14:30:00",
        "point": 3000,
        "standardAmount": 15000,
        "storeName": "김밥천국"
      },
      {
        "missionId": 3,
        "endDate": "2025-12-20T14:30:00",
        "point": 5000,
        "standardAmount": 15000,
        "storeName": "떡볶이나라"
      }
    ],
    "listSize": 10,
    "totalPage": 1,
    "totalElements": 3,
    "isFirst": true,
    "isLast": true
  }
}
```
###### 진행 중
```json
{
  "isSuccess": true,
  "code": "MISSION302_1",
  "message": "미션을 조회하였습니다.",
  "result": {
    "missionList": [
      {
        "missionId": 1,
        "endDate": "2025-12-20T14:30:00",
        "point": 6000,
        "standardAmount": 20000,
        "storeName": "김밥천국"
      },
      {
        "missionId": 3,
        "endDate": "2025-12-20T14:30:00",
        "point": 5000,
        "standardAmount": 15000,
        "storeName": "떡볶이나라"
      }
    ],
    "listSize": 10,
    "totalPage": 1,
    "totalElements": 2,
    "isFirst": true,
    "isLast": true
  }
}
```
###### 진행 완료
```json
{
  "isSuccess": true,
  "code": "MISSION302_1",
  "message": "미션을 조회하였습니다.",
  "result": {
    "missionList": [
      {
        "missionId": 2,
        "endDate": "2025-12-20T14:30:00",
        "point": 3000,
        "standardAmount": 15000,
        "storeName": "김밥천국"
      }
    ],
    "listSize": 10,
    "totalPage": 1,
    "totalElements": 1,
    "isFirst": true,
    "isLast": true
  }
}
```
