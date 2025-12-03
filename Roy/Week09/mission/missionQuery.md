## 특정 가게의 미션 목록 조회하기

### 1. req
page, memberId
### 2. res
```json
{
  "페이지 번호",
  "미션 개수",
  "리뷰 리스트"
}
```
### 3. validate
```json
{
  "storeId": @ExistStore,
}
```
### 4. 트러블 슈팅
- erd 설계시 가게 당 하나의 미션이 있을 거라고 생각 해 oneToone 매핑을 했었는데
- 미션 목록이라고 해서 ManyToone으로 변경
```java
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id",nullable = false)
  private Store store;
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
```json
{
  "isSuccess": true,
  "code": "MISSION302_1",
  "message": "미션을 조회하였습니다.",
  "result": {
    "missionList": [
      {
        "missionId": 1,
        "endDate": "2025-12-01T14:30:00",
        "point": 3000,
        "standardAmount": 15000,
        "storeName": "김밥천국"
      },
      {
        "missionId": 2,
        "endDate": "2025-12-20T14:30:00",
        "point": 6000,
        "standardAmount": 20000,
        "storeName": "김밥천국"
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

