package com.project.dugeun.domain.signup.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class EmailSendRequestDto
{

    @NotEmpty
    private String email;

    @NotEmpty
    private String uniName;
}
