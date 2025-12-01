### 회원가입 (소셜 로그인 제외)
**API Endpoint** : POST /auth/users/signup
    
**Request Header** : Content-Type: application/json
    
**Request Body** 
  ```json
    {
      "loginId": "test1234",
      "password": "1234",
      "name": "김세은",
      "nickname": "오잉",
      "email": "test@naver.com",
      "gender": "FEMALE",
      "birth": "2003-12-06",
      "address": "경상북도 포항시",
      "phoneNumber": "010-1234-5678"
}
  ```
**query String** :  X.
    
**Path variable** : X. (사용자 식별자 존재x)

-------------------------
### 홈화면
**API Endpoint** : GET /users/{userId}/missions
    
**Request Header** : Authorization : Bearer {accessToken}
    
**Request Body**  : X.

**query String** :  /users/{userId}/missions?view=home (홈화면이므로 view이름을 home으로)
    
**Path variable** : {userId}

-------------------------

### (마이페이지) 리뷰 작성
**API Endpoint** : POST /missions/{missionId}/reviews
    
**Request Header** : Authorization : Bearer {accessToken}
    
**Request Body** 
  ```json
    { 
          "rating" : 4,
          "review_text" : "음식이 맛있고 식당 분위기도 좋아요!"
          "photo_url" : "(이미지 파일 url)"
    }
  ```
**query String** :  X.
    
**Path variable** : {missionId}

-------------------------

### 미션 목록 조회
**API Endpoint** : GET /users/me/missions
    
**Request Header** : Authorization : Bearer {accessToken}
    
**Request Body** : X.
  
**query String** :  /missions?status=in-process (진행 중), /missions?status=completed (진행 완료)
    
**Path variable** : x.

-------------------------

### 미션 성공 누르기
**API Endpoint** : PATCH /missions/{missionId}/complete
    
**Request Header** : Authorization : Bearer {accessToken}
    
**Request Body** : X.
  
**query String** : X.
    
**Path variable** : {missionId}
