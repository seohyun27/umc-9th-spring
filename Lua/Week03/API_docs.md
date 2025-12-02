### 회원가입 (소셜 로그인 제외)
**API Endpoint** : `POST /auth/users/signup`
    
**Request Header** : `Content-Type: application/json`
    
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
**query String** :  `X`
    
**Path variable** : `X`

-------------------------
### 홈화면
**API Endpoint** : `GET /missions`
    
**Request Header** : `Authorization : Bearer {accessToken}`
    
**Request Body**  : `X`

**query String** :  `region`, `page`, `size` ex) `/missions?region=안암동&page=1&size=10`
    
**Path variable** : `X`

-------------------------
### 마이 페이지
**API Endpoint** : `GET /users/profile`
    
**Request Header** : `Authorization : Bearer {accessToken}`
    
**Request Body** : `X`

**query String** :  `X`
    
**Path variable** : `X`

-------------------------

### (마이페이지) 리뷰 작성
**API Endpoint** : `POST /users/me/reviews`
    
**Request Header** : `Authorization : Bearer {accessToken`},`Content-Type: application/json`
    
**Request Body** 
  ```json
    { 
          "shopId" : 126,
          "reviewText" : "음식이 맛있고 식당 분위기도 좋아요!"
          "rating" : "5.0"
          "reviewImages": [
            "https://images/photo1.jpg"
            "https://images/photo2.jpg"
         ]
    }
  ```
**query String** :  `X`
    
**Path variable** : `X`

-------------------------

### 미션 목록 조회
**API Endpoint** : `GET /users/me/missions`
    
**Request Header** : `Authorization : Bearer {accessToken}`
    
**Request Body** :`X`
  
**query String** :  `status`, `page`, `size` ex)`/users/me/missions?status=CHALLENGING&page=1&size=10`
    
**Path variable** : `X`

-------------------------

### 미션 성공 누르기
**API Endpoint** : PATCH /missions/{missionId}/complete
    
**Request Header** : Authorization : Bearer {accessToken}
    
**Request Body** : X.
  
**query String** : X.
    
**Path variable** : {missionId}
