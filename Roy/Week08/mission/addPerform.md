## 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)

### 1. req

```json
{
  "mission_id": 미션 아이디,
  "member_id" : 멤버 아이디
}
```
+ 이건 그냥 수행 테이블에 미션을 추가하는 것이므로 두개밖에 안 필요함
+ status 는 default로 되고 당연히 finished_at은 null이 됨

### 2. res
```json
{
  "id": 수행 아이디,
  "createAt": 미션을 수행에 추가한 날짜,
}
```
### 3. valid
```json
@NotExistPerform
{
  "missionId": @ExistMission,
  "memberId" : @ExistMember
}
```
### 4. 트러블 슈팅
##### 1) dto로 할지 path로 할지 고민 -> path로 받고 dto로 검증
+ dto로 하려고 했는데 path로 넣으래서 path_variable로 받음
+ path에 바로 valid쓰면 다른 에러 터지고 에러 json을 순서대로 처리할 수 가 없어서 찾아봤는데
+ 대박적인게 record로 하면 path들을 한번에 dto로 매핑이 가능함
```java
@RequestMapping("/users/{memberId}/mission")
public class PerformController {
    @PostMapping("/{missionId}/add")
    public ApiResponse<PerformResDTO.registerDTO> addPerform(
            @Valid @ModelAttribute PerformReqDTO.registerDTO dto
    ) {}
}
```
이게 가능함!!
##### 2) controller를 미션에 합칠지 따로 만들지 고민했는데 상위 동작이 위로 가야 된다고 해서 분리
##### 3) mission은 전체 미션 작업, perform은 user가 건드리는 거로 정의내림



### 5. 동작 확인(빠르게 확인하기 위해 .http 사용)
##### 1) 존재하지 않을 때 valid check : @exist
###### -> 기대 : member,store에서 에러 표기
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "missionId": "미션이 존재하지 않습니다.",
    "memberId": "해당 사용자를 찾지 못했습니다."
  }
}
```
##### 2) 이미 존재할 때 : @alreadyExist
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "registerDTO": "이미 추가한 미션입니다."
  }
}
```
+ dto 상위의 어노테이션은 필드 에러가 아니라서 상위 에러도 잡을 수 있게 함
```java
        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put(error.getObjectName(), error.getDefaultMessage())
        );
```
##### 5) 가게 등록 : 모두 정상 입력값을 넣어줬을 때
###### -> 기대 : 200 ok , DB 정상 반영
###### -> 실제 응답
```json
{
  "isSuccess": true,
  "code": "PERFORM200_1",
  "message": "미션을 나의 미션에 추가하였습니다.",
  "result": {
    "performId": 2,
    "creatAt": "2025-11-29T15:46:46.1802471"
  }
}
```
###### -> DB
```json
[
  {
    "perform_id": 1,
    "finished_at": null,
    "mission_status": false,
    "member_id": 2,
    "mission_id": 1,
    "created_at": "2025-11-29 15:37:45.516283",
    "updated_at": "2025-11-29 15:37:45.516283"
  },
  {
    "perform_id": 2,
    "finished_at": null,
    "mission_status": false,
    "member_id": 1,
    "mission_id": 1,
    "created_at": "2025-11-29 15:46:46.180247",
    "updated_at": "2025-11-29 15:46:46.180247"
  }
]
```


