package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.entity.mapping.UserPrefer;
import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.domain.user.enums.UserStatus;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "login_id", length = 10, nullable = false)
    private String loginId;

    @Column(name = "password", length = 15, nullable = false)
    private String password;

    @Column(name = "inactive_time")
    private LocalDateTime inactiveTime;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "point")
    @Builder.Default
    private Integer point = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<UserPrefer> userPrefers = new ArrayList<>();
}