package com.project.dugeun.domain.blindDate.dto;

import com.project.dugeun.domain.blindDate.domain.BlindDateResult;
import lombok.Getter;

import java.util.Optional;

@Getter
public class MatchingResponseDto {
    private Boolean isMatching;
    private String otherExternalId;

    public MatchingResponseDto(Optional<BlindDateResult> blindDateResult, String userId){

        if(!blindDateResult.isPresent()){
            isMatching = true;
            return;
        }

        isMatching = true;
        if(blindDateResult.get().getUser().getUserId().equals(userId)) {
        otherExternalId = blindDateResult.get().getAnotherUser().getExternalId();
        }else{
            otherExternalId = blindDateResult.get().getUser().getExternalId();
        }

    }
}
