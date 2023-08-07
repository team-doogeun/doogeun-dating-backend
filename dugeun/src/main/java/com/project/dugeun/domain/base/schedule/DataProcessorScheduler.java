package com.project.dugeun.domain.base.schedule;

import com.project.dugeun.domain.blindDate.application.MatchMakerService;
import com.project.dugeun.domain.finalMatch.application.FinalMatchService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataProcessorScheduler {
    private final FinalMatchService finalMatchService;
    private final MatchMakerService matchMakerService;
    private final UserRepository userRepository;

    public DataProcessorScheduler(FinalMatchService finalMatchService, MatchMakerService matchMakerService, UserRepository userRepository){
        this.finalMatchService = finalMatchService;
        this.matchMakerService = matchMakerService;
        this.userRepository = userRepository;
    }

    @Transactional
    @Scheduled(cron = "0 0 02 * * ?") // 매일 `새벽 2시 마다
    public void processMatchDate(){
        // user 디비에 있는 모든 유저들 로드해서 수행
       List<User> users = userRepository.findAll();
       for(User user: users){
           // 모든 사용자에 대해 소개 상대 소개 초기화
           matchMakerService.manageMatches(user);
       }
    }


    @Transactional
    @Scheduled(cron = "0 */5 * * * *") // 매일 5분 마다
    public void processFinalMatchDate(){

        List<User> users = userRepository.findAll();
        for(User user: users){
            finalMatchService.saveFinalMatch(user.getUserId());
        }

    }

}
