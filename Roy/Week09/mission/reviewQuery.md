## 내가 쓴 리뷰 조회하기

### 1. req
page, memberId
### 2. res
```json
{
  "페이지 번호",
  "리뷰 개수",
  "리뷰 리스트"
}
```
### 3. validate
- 멤버가 있는지, page가 유효한지 validate를 controller에서 하면 또 에러를 다 잡아야 되기 때문에
- 저번 8주차를 참고해서 dto로 받음 **8주차 미션의 addPerform 참고**
```json
{
  "memberId": @ExistMember,
}
```
### 4. 트러블 슈팅
- 조회 하는 건데 굳이 에러코드가 필요할까
### 5. 동작 확인(빠르게 확인하기 위해 .http 사용)
##### 1) 일반 valid check : 모두 null이거나 blank
###### -> 기대: managerId 에러 표기해야 함
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "memberId": "해당 사용자를 찾지 못했습니다."
  }
}
```
##### 5) 리뷰 조회
###### -> 기대 : 200 ok , DB 정상 반영
###### -> 실제 응답
```json
{
  "isSuccess": true,
  "code": "REVIEW302_1",
  "message": "리뷰를 조회하였습니다.",
  "result": {
    "reviewList": [
      {
        "id": 3,
        "writer": "Roy",
        "rate": 3,
        "content": "너무 맛있어요",
        "storeName": "김밥천국",
        "createdAt": "2025-11-25T18:18:02.792455"
      },
      {
        "id": 4,
        "writer": "Roy",
        "rate": 1,
        "content": "너무 맛없음",
        "storeName": "떡볶이나라",
        "createdAt": "2025-11-25T18:31:27.071543"
      },
      {
        "id": 5,
        "writer": "Roy",
        "rate": 3,
        "content": "너무 맛있어요",
        "storeName": "김밥천국",
        "createdAt": "2025-11-28T15:41:44.842108"
      }
    ],
    "listSize": 3,
    "totalPage": 1,
    "totalElements": 3,
    "isFirst": true,
    "isLast": true
  }
}
```

