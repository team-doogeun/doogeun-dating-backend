//package com.project.dugeun.config;
//
//import com.project.dugeun.domain.blindDate.application.MatchMaker;
//import com.project.dugeun.domain.finalMatch.application.FinalMatchService;
//import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
//import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
//import com.project.dugeun.domain.user.dao.UserRepository;
//import com.project.dugeun.domain.user.domain.User;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class DataProcessorScheduler {
//
//    private final FinalMatchService finalMatchService;
//    private final LikeablePersonRepository likeablePersonRepository;
//
//    private final MatchMaker matchMaker;
//    private final FinalMatchRepository finalMatchRepository;
//    private final UserRepository userRepository;
//
//    public DataProcessorScheduler(FinalMatchService finalMatchService, LikeablePersonRepository likeablePersonRepository, MatchMaker matchMaker, FinalMatchRepository finalMatchRepository, UserRepository userRepository){
//        this.finalMatchService = finalMatchService;
//        this.likeablePersonRepository = likeablePersonRepository;
//        this.matchMaker = matchMaker;
//        this.finalMatchRepository = finalMatchRepository;
//        this.userRepository = userRepository;
//    }
//
////    @Scheduled(cron = "0 0 10 * * *") // 매일 오전 10시에 실행되도록 설정
//    @Transactional
//    @Scheduled(cron = "0 0/1 * * * *") // 매일 `분마다 실행
//    public void processDate(){
//
//        // 이전의 finalMatch에 저장된 것들 다 지우기
////        finalMatchRepository.deleteAll();
//
//
//
//
//        // user 디비에 있는 모든 유저들 로드해서 수행
//       List<User> users = userRepository.findAll();
//       for(User user: users){
//           // 모든 사용자에 대해 소개 상대 소개 초기화
//           matchMaker.manageMatches(user);
//           // 최종 매칭 저장
//           finalMatchService.saveFinalMatch(user.getUserId());
//
//       }
//
//        System.out.println("Data processing job executed!!!!!!!!");
//
//
//    }
//
//}
