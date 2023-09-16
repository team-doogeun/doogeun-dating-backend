package com.project.dugeun.domain.signup.dto;

import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserSaveRequestDto {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String description;

    @NotEmpty
    private String externalId;

    @NotEmpty
    private String uniName;

    @NotEmpty
    private String studentId;

    @Email
    @NotEmpty
    private String email;

    @NotNull
    private Integer age;

    @NotNull
    private GenderType gender;

    @NotNull
    private DetailProfile detailProfile;

    @NotNull
    private IdealTypeProfile idealTypeProfile;

    private String basicFilePath;
    private String secondFilePath;
    private String thirdFilePath;


    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .name(this.name)
                .password(this.password)
                .description(this.description)
                .externalId(this.externalId)
                .uniName(this.uniName)
                .studentId(this.studentId)
                .email(this.email)
                .age(this.age)
                .gender(this.gender)
                .detailProfile(this.detailProfile)
                .idealTypeProfile(this.idealTypeProfile)
                .basicFilePath(this.basicFilePath)
                .secondFilePath(this.secondFilePath)
                .thirdFilePath(this.thirdFilePath)
                .build();
    }
}
