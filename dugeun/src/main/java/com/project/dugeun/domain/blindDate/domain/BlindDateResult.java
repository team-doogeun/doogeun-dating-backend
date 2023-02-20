package com.project.dugeun.domain.blindDate.domain;

import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class BlindDateResult {

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

    @Override
    public String toString(){
        return String.format("(%d,%d)", user.getId(), anotherUser.getId());
    }

    public static List<BlindDateResult> getResultsFrom(List<Pair<User, User>> matchingPairs, LocalDate localDate) {

        List<BlindDateResult> matchingResults = new ArrayList<>();

        Iterator<Pair<User, User>> iterator = matchingPairs.iterator();
        while (iterator.hasNext()) {
            Pair<User, User> next = iterator.next();
            BlindDateResult build =  BlindDateResult.builder()
                    .user(next.getFirst())
                    .anotherUser(next.getSecond())
                    .matchingDate(localDate)
                    .build();

            matchingResults.add(build);
        }

        return matchingResults;
    }
}
