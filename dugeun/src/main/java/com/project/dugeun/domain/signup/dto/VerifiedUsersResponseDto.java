package com.project.dugeun.domain.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifiedUsersResponseDto {

    private List<String> verifiedEmails;

}
