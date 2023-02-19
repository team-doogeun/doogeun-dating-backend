package com.project.dugeun.model.dto;

import com.project.dugeun.model.domain.user.User;
import com.project.dugeun.model.domain.user.profile.DetailProfile;
import com.project.dugeun.model.domain.user.profile.IdealTypeProfile;
import com.project.dugeun.model.domain.user.profile.category.GenderType;
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
    private String externalId;

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

    public User toEntity(){
        return User.builder()
                .userId(getUserId())
                .name(getName())
                .password(getPassword())
                .confirmPassword(getConfirmPassword())
                .externalId(getExternalId())
                .studentId(getStudentId())
                .email(getEmail())
                .age(getAge())
                .gender(getGender())
                .detailProfile(getDetailProfile())
                .idealTypeProfile(getIdealTypeProfile())
                .basicFilePath(getBasicFilePath())
                .secondFilePath(getSecondFilePath())
                .thirdFilePath(getThirdFilePath())

                .build();
    }
}
