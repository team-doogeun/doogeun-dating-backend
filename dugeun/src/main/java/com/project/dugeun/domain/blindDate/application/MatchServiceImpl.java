package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.dao.ParticipantRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.blindDate.domain.Participant;
import com.project.dugeun.domain.signup.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {


    private final MatchFilter matchFilter;
    private final ParticipantRepository participantRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Transactional
    @Override
    public void processMatching() { //  모든 참가자들에 대한 매칭 진행
        List<Participant> all = participantRepository.findAll();

        // 참여한 유저들의 List
        List<User> users = all.stream().map(
                (x) -> x.getUser()).collect(Collectors.toList());
        logger.info("match count (participant) : " + users.size());


      //  List<Pair<User,User>> matchPair = matchFilter에서의 조작

    }


    @Transactional
    @Override
    public Optional<Match> findMatch(String userId) { // 특정 참가자의 매칭 조회

        //
        User user = userRepository.findByUserId(userId);

        // 마지막 매칭 날을 확인해서 없으면 오류 처리


        Match match = matchRepository.findByAnotherUserIdAndMatchDate(user.getUserId(), user.getLastMatchDate()).
                orElseGet(() ->
                        matchRepository.findByUserIdAndMatchDate(user.getUserId(),user.getLastMatchDate())
                                .orElse(null));

        return Optional.ofNullable(match);
    }
}
