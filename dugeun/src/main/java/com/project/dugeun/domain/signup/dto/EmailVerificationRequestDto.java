package com.project.dugeun.domain.signup.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class EmailVerificationRequestDto
{
    @NotEmpty
    private Integer code;

}
