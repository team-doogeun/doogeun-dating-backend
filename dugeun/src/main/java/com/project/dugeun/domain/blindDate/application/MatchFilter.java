package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchFilter {

    private final int MATCH_DAY = 5; // FRIDAY


//    public static List<Pair<User,User>> applyFilter(List<User> users){
//
//    }

    // 위치가 같으면


    // 다음 매칭 날 구하기
    public LocalDate getNextMatchDate(LocalDate now){
        int next = MATCH_DAY  - now.getDayOfWeek().getValue();
        next = next < 0 ? 7 + next : next;
        int remindDay = next == 0 ? 7 : next;

        LocalDate nextMatchDate = now.plusDays((long)remindDay);
        return nextMatchDate;
    }

}
