## 가게에 리뷰 추가하기

### 1. 리뷰 엔티티(req)
- 리뷰의 외래 키는 멤버, 리뷰 사진이 있다.
- 답글은 엔티티를 분리를 안 시키고 나는 리뷰 하나당 사장님 답글 하나만 등록한다고 가정함
- 필요하다면 추후 수정 예정
```json
{
  "rate" : 별점,
  "content" : 리뷰 내용,
  "storeId": 가게 아이디,
  "memberId" : 멤버 아이디,
  "reviewPhotos" : 리뷰 사진 리스트,
}
```
### 2. res
```json
{
  "id": 리뷰 아이디,
  "createAt": 리뷰 생성시각
}
```
### 3. valid
```json
{
  "rate" : @MAX(5),
  "content" : @SIZE(200),
  "storeId": @ExistStore @NOTNULL,
  "memberId" : @ExistMember @NOTNULL,
  "reviewPhotos" : @SIZE(3)
}
```
+ 생각해보니까 첫번째 미션에서 멤버가 있는지 검사하고 권한이 있는지 검사하는 걸 한 어노테이션으로
+ 처리했었는데 지금 또 쓰이니까 Exist는 분리하는 게 좋을 듯 -> 첫번째 미션도 수정!
+ size가 리스트 길이도 제한해준다는 것을 추가적으로 알게 됨
### 4. 트러블 슈팅
- 그냥 내 코드 전체가 궁금함... 잘 짠건지 모르겠음
#### 4.1) 리뷰 사진 등록하기
- 처음엔 리뷰 사진을 등록하고 리뷰를 등록해야 되는지 리뷰를 등록하고 사진을 등록해야 되는지 
- 어려움이 있었는데 엔티티에 cascade 옵션을 적어주면 review 생성시 reviewPhoto에도 자동으로
- reviewId가 들어간다고 함
#### 4.2 ) 리뷰 쿼리 dsl, jpa 모두 상속 받는 레포 만들기
- 저번주에 의존성을 이해 못해서 서비스에서 구현체를 상속 받았었다
- 인터페이스만 상속해주면 구현체는 알아서 주입해준 다는 것을 알았긴 한데 의존성이 너무 어렵다.
```java
public interface ReviewRepository extends JpaRepository<Review, Long>,ReviewQueryDsl{}
```

### 5. 동작 확인(빠르게 확인하기 위해 .http 사용)
##### 1) 일반 valid check : 모두 null이거나 blank
###### -> 기대: store,member에서 모두 에러 표기해야 함
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "storeId": "널이어서는 안됩니다",
    "memberId": "널이어서는 안됩니다"
  }
}
```
##### 2) max,size valid check
###### -> 기대 : address,name에서 에러 표기
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "rate": "5 이하여야 합니다",
    "reviewPhotos": "크기가 0에서 3 사이여야 합니다",
    "content": "크기가 0에서 200 사이여야 합니다"
  }
}
```
##### 3) 존재하지 않을 때 valid check : @exist
###### -> 기대 : member,store 에러 표기
###### -> 실제 응답
```json
{
  "isSuccess": false,
  "code": "VALID400_1",
  "message": "검증에 실패했습니다.",
  "result": {
    "storeId": "가게가 존재하지 않습니다.",
    "memberId": "해당 사용자를 찾지 못했습니다."
  }
}
```
##### 4) 리뷰 등록 : 모두 정상 입력값을 넣어줬을 때
###### -> 기대 : 200 ok , DB 정상 반영
###### -> 실제 응답
```json
{
  "isSuccess": true,
  "code": "REVIEW201_1",
  "message": "리뷰가 등록되었습니다.",
  "result": {
    "reviewId": 3,
    "createAt": "2025-11-25T18:15:24.5462024"
  }
}
```
###### -> reviewPhoto도 잘 들어갔는지 확인 : 일단 url은 그냥 아무 문자열 넣음
```markdown
+---------------+---------+---------+
|review_photo_id|photo_url|review_id|
+---------------+---------+---------+
|4              |d        |3        |
|5              |d        |3        |
|6              |d        |3        |
|7              |d        |4        |
|8              |d        |4        |
|9              |d        |4        |
+---------------+---------+---------+
```
###### -> 지난번에 만들어 놓은 별점, 가게로 조회가 되는지 확인
GET reviews/search?query="3"&type="star"
```json
{
  "isSuccess": true,
  "code": "COMMON200",
  "message": "성공적으로 요청을 처리했습니다.",
  "result": [
    {
      "id": 3,
      "rate": 3,
      "content": "너무 맛있어요",
      "storeName": "김밥천국"
    }
  ]
}
```
GET reviews/search?query=떡볶이&type=store
```json
{
  "isSuccess": true,
  "code": "COMMON200",
  "message": "성공적으로 요청을 처리했습니다.",
  "result": [
    {
      "id": 4,
      "rate": 1,
      "content": "너무 맛없음",
      "storeName": "떡볶이나라"
    }
  ]
}
```

