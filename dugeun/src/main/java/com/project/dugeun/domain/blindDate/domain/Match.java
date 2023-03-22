package com.project.dugeun.domain.blindDate.domain;

import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="another_user_id")
    private User anotherUser;

    private LocalDate matchingDate;


    // 매칭된 페어들과 다음 매칭 날자를 매개변수로 받아 돌아가며 matchResults에 추가를 함
    public static List<Match> getResults(List<Pair<User,User>> matchPairs, LocalDate localDate){
        List<Match> matchResults = new ArrayList<>();

        Iterator<Pair<User,User>> iterator = matchPairs.iterator();
        while(iterator.hasNext()){
            Pair<User,User> next = iterator.next();
            Match build = Match.builder()
                    .user(next.getFirst())
                    .anotherUser(next.getSecond())
                    .matchingDate(localDate)
                    .build();

            matchResults.add(build);

        }

        return matchResults;

    }
}
