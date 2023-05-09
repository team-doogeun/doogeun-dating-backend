package com.project.dugeun.domain.user.domain;


import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import com.project.dugeun.domain.signup.dto.UserSaveRequestDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Entity
//@Table(name= "com/project/dugeun/domain/user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 자동 생성되는 유저 id

    @Column(name="user_id", unique = true)
    private String userId; // 유저 아이디

    @Column(name="name",nullable = false,unique = true,length = 30)
    private String name; // 유저 닉네임

    @Column(name="password")
    private String password;

    private String confirmPassword;

    @Column(name="external_id", unique = true)
    private String externalId; // 카카오아이디

    @Column(name="student_id")
    private String studentId; // 학번


    @Column(name="email")
    private String email;

    @Column(name="age")
    private Integer age;

    @Column(name="gender")
//    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private GroupBlindRoom groupBlindRoom;


    @Embedded
    private DetailProfile detailProfile;

    @Embedded
    private IdealTypeProfile idealTypeProfile;

    @Column(name = "basic_profile_image")
    private String basicFilePath;

    @Column(name = "second_profile_image")
    private String secondFilePath;
    @Column(name = "third_profile_image")
    private String thirdFilePath;

//
//
//    @OneToMany
//    private Match match;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<Like> likeList = new ArrayList<>();


    @Builder
    public User(String userId, String name, String externalId, String password,String confirmPassword, String studentId,String email,
                Integer age, GenderType gender, DetailProfile detailProfile, IdealTypeProfile idealTypeProfile,
                String basicFilePath, String secondFilePath, String thirdFilePath
                ){
        this.userId = userId;
        this.name = name;
        this.externalId = externalId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.studentId = studentId;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.detailProfile = detailProfile;
        this.idealTypeProfile = idealTypeProfile;
        this.basicFilePath = basicFilePath;
        this.secondFilePath = secondFilePath;
        this.thirdFilePath = thirdFilePath;

    }

    public static User createUser(UserSaveRequestDto userFormDto, PasswordEncoder passwordEncoder){
        User user = User.builder()
                .name(userFormDto.getName())
                .externalId(userFormDto.getExternalId())
                .studentId(userFormDto.getStudentId())
                .email(userFormDto.getEmail())
                .age(userFormDto.getAge())
                .gender(userFormDto.getGender())
                .detailProfile(userFormDto.getDetailProfile())
                .idealTypeProfile(userFormDto.getIdealTypeProfile())
                .password(passwordEncoder.encode(userFormDto.getPassword())

                )
                .build();
        return user;
    }
}


