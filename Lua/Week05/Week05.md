
##### 리뷰 작성하는 쿼리
```sql
INSERT INTO review (user_id, shop_id, review_text, rating, created_at)
VALUES (?, ?, ?, ?, NOW());
```

### //ReviewRepository.java
```java
package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
```
------------
##### 마이페이지 화면 쿼리 
```sql
SELECT nickname, email, phone_number, point
FROM user
WHERE user_id=?;
```

### //UserRepository.java (사용자 정보, 리뷰만)

```java
package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
```
--------------
##### 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
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

### // UserMissionRepository.java (미션 모아서 보는 쿼리)

```java
package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findAllByUserAndIsSuccess(User user, Boolean isSuccess, Pageable pageable);
}
```
---------------
##### 홈 화면 쿼리
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
### // MissionRepository.java (홈화면 쿼리)
```java
package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
           "JOIN m.shop s " +
           "JOIN s.shopRegionList sr " +
           "JOIN sr.region r " +
           "WHERE r.name = :regionName " +
           "AND m.status = :status " +
           "AND m.deadline > :now")
    Page<Mission> findMissionsByRegion(
            @Param("regionName") String regionName,
            @Param("status") MissionStatus status,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );
}
```
