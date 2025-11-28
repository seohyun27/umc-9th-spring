## 리뷰 추가하기

### 1. 미션 엔티티(req)

```json
{
  "point": 미션시 획득 포인트,
  "standardAmount": 미션 참여로 인정되는 기준 금액,
  "endDate": 미션 종료 시간,
  "storeId": 가게 아이디,
  "managerId": 매니저 아이디,
}
```
+ jwt를 쓴다면 manager가 auth 헤더로 들어가겠지만 아직 구현을 안 했기 때문에 
+ 직접 넣어주는 식으로 권한 체크만 함
### 2. res
```json
{
  "id": 미션 아이디,
  "createAt": 미션 생성날짜
}
```
### 3. valid
```json
{
  "endDate": @NotNull @validDuration,
  "storeId": @NotNull @existStore,
  "managerId": @NotNull @existMember, @managerPermission
}
```
+ validDuration은 끝나는 일정이 생성 시점보다 큰지
+ 나머지는 디폴트 값들이므로 체크해야 할 건 3개밖에 없음
+ 매니저는 멤버가 있는지, 매니저 권한이 있는지, 해당 가게의 매니저인지 총 3개의 valid를 
+ 넣었는데 이렇게 많이 쪼개나 싶긴 함.. -> 2개만 함
+ 원래 해당 가게의 매니저인지도 validator로 넣으려고 했는데 단일 연산이 아닌 DB 조회는 트랜잭션이 엉킬 수가 있어서
+ 서비스에서 처리하는 게 낫다고 함

### 4. 트러블 슈팅

### 5. 동작 확인(빠르게 확인하기 위해 .http 사용)
##### 1) 일반 valid check : 모두 null이거나 blank
###### -> 기대: endDate,managerId,storeId에서 모두 에러 표기해야 함
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "endDate": "널이어서는 안됩니다",
    "managerId": "널이어서는 안됩니다",
    "storeId": "널이어서는 안됩니다"
  }
}
```
##### 2) 존재하지 않을 때 valid check : @exist
###### -> 기대 : member,store에서 에러 표기
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "managerId": "해당 사용자를 찾지 못했습니다.",
    "storeId": "가게가 존재하지 않습니다."
  }
}
```
##### 4) 권한 valid check : @permission
###### -> 기대 : member에서 권한 오류 (매니저 권한이 아닌 일반 사용자로 넣었을 때)
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "managerId": "권한이 없습니다."
  }
}
```
##### 5) 가게 등록 : 모두 정상 입력값을 넣어줬을 때
###### -> 기대 : 200 ok , DB 정상 반영
###### -> 실제 응답
```json
{
  "isSuccess": true,
  "code": "MISSION201_1",
  "message": "미션을 등록하였습니다.",
  "result": {
    "missionId": 1,
    "createAt": "2025-11-28T15:35:27.1007699"
  }
}
```
###### -> DB
```json
[
  {
    "mission_id": 1,
    "created_at": "2025-11-28 15:35:27.100770",
    "updated_at": "2025-11-28 15:35:27.100770",
    "end_date": "2025-11-29 14:30:00.000000",
    "mission_point": 3000,
    "standard_amount": 15000,
    "store_id": 1
  }
]
```


