package com.project.dugeun.domain.blindDate.domain.dto;

import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDto {

    private List<String> userIds;
    private List<String> names;


    public MatchResponseDto(List<User> users){
        users.forEach(user->{
            if(user.getUserId()!=null){
                //  매칭된 userId를 리스트에 저장
                userIds.add(user.getUserId());
                names.add(user.getName());
            }
        });
    }

}
