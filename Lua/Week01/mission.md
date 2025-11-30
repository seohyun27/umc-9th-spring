### 1. 0주차 때 **직접 설계한** 데이터베이스를 토대로 아래의 화면에 대한 쿼리를 작성
#### - 설계한 DB 사진 (아래 폴더들 참고하기)
<img width="210" height="167" alt="image" src="https://github.com/user-attachments/assets/c48c9fb2-4f88-4b76-a86e-25c809190f90" />

#### 리뷰 작성하는 쿼리
```sql
INSERT INTO review (user_id, shop_id, review_text, rating, created_at)
VALUES (?, ?, ?, ?, NOW());
```

#### 마이페이지 화면 쿼리 
```sql
SELECT nickname, email, phone_number, point
FROM user
WHERE user_id=?;
```

#### 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
```sql
SELECT 
    s.name AS shop_name,
    m.mission_spec,
    m.mission_point,
    um.status,
    um.created_at
FROM user_mission AS um
JOIN mission AS m ON um.mission_id = m.mission_id
JOIN shop AS s ON m.shop_id = s.shop_id
WHERE um.user_id = ?
  AND um.status = ?
ORDER BY um.created_at DESC
LIMIT ? OFFSET ?;
```

#### 홈 화면 쿼리
```sql
SELECT 
    s.name AS shop_name,
    m.mission_spec,
    m.mission_point,
    m.deadline
FROM mission AS m
JOIN shop AS s ON m.shop_id = s.shop_id
JOIN shop_local AS sl ON s.shop_id = sl.shop_id
JOIN local AS l ON sl.local_id = l.local_id
WHERE l.local_keyword = ?
  AND m.status = 'ACTIVE'
  AND m.deadline > NOW()
ORDER BY m.mission_id DESC
LIMIT ? OFFSET ?;
```
