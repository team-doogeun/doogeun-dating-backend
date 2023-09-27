package com.project.dugeun.domain.chat;

import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    @Id
    @GeneratedValue
    @Column(name ="room_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="final_match_id")
    private FinalMatch finalMatch;

}
