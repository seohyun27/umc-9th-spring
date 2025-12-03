## 미션 완료하기 -> 조회

### 1. req
memberId,missionId
### 2. res
```json
{
  미션 정보, 완료 날짜
}
```
### 3. validate
memberId, missionId 검사
### 4. 트러블 슈팅
- performId로 가져올 지 복합키로 가져올 지 고민했는데 이미 api 경로 상에 memberId가 있어서 복합으로 함..
- 처음에 dto는 잘 가져오는데 db에 반영이 안되길래 왜 안되지? 했었는데 transactional을 안 넣음... 영속성 안에서만 있는 거였음
### 5. 동작 확인(빠르게 확인하기 위해 .http 사용)
valid는 이미 앞에서 많이 체크했기 때문에 따로 동작 테스트는 안 함
###### 1 ) 나의 미션에 없는 미션일 때
```json
{
  "isSuccess": false,
  "code": "PERFORM404_1",
  "message": "나의 미션에 추가되지 않았습니다.",
  "result": null
}
```
###### 2 ) 이미 완료했을 때
```json
{
  "isSuccess": false,
  "code": "PERFORM400_2",
  "message": "이미 완료한 미션입니다.",
  "result": null
}
```
###### 3) 완료 동작
```json
{
  "isSuccess": true,
  "code": "PERFORM200_2",
  "message": "미션을 완료하였습니다.",
  "result": {
    "mission": {
      "missionId": 1,
      "endDate": "2025-12-20T14:30:00",
      "point": 6000,
      "standardAmount": 20000,
      "storeName": "김밥천국"
    },
    "status": "COMPLETED",
    "finishedAt": "2025-12-03T12:12:26.5950776"
  }
}
```