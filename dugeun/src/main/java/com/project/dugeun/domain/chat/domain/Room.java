package com.project.dugeun.domain.chat.domain;

import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor()
public class Room {
    @Id
    @GeneratedValue
    @Column(name ="room_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="final_match_id")
    private FinalMatch finalMatch;

}
