
# 두근프로젝트

## 서비스 소개
<img width="800" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/10598f67-6d43-4ae1-9d0f-050456590c70">     


**‘두근’,** 팀명이자 프로젝트명으로 단어 자체가 가지는 의미처럼 저희 서비스를 이용하며 학생들이 서로 두근 거리며 즐거운 경험을 하였으면 하는 마음으로 이번 프로젝트를 기획했습니다.

### What ? 
1. **하루에 한 번 내 이상형 2명 소개 받기 !** 사용자가 입력한 정보에 따라, 사용자의 이상형을 기반으로 이상형에 가까운 소개를 소개해줍니다.
2. **자유롭게 미팅방 생성 및 참가 가능 !** 2:2, 3:3, 4:4까지 누구나 호스트가 되어 자유롭게 방을 개설할 수 있고 참여하여 많은 실제 미팅 분위기를 만들 수 있습니다 
 
## 🔧 Tools 
<img width="300" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/56c62121-d58b-4c17-9ff2-df2c6f5ab28b">     

## 테이블 (다이어그램)  
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/d9531e44-57e6-4691-a1a7-be0e5108c3e4">     

## API명세 
https://caramel-beast-94d.notion.site/api-72d47c6a90fb4264a220edd1e62c6c4d?pvs=4


## CI/CD
<img width="478" alt="CI/CD" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/b21240ea-a57a-44b1-bb13-5a589532a903"> 


##  💻  프로젝트 설명 (주요 기능)
### 1. 회원 가입 및 회원 정보 입력
- 기본 정보(userId,name,age,uniName등..) 입력 받기
- 학교 이메일 인증을 통한 대학생 인증
- 상세 정보(취미,성격,키,체형,mbti,흡연 유무,음주 정도,이상형 우선순위등..) 입력받기
- 이상형 정보(선호하는 상대의 취미,성격,키,체형,mbti,흡연 유무,음주 정도등...) 입력받기
<img width="250" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/28ec2e86-9536-4861-9215-5df18d6da0be">      
<img width="150" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/ec8e9d63-1422-4a95-8f43-d1459828c417">
<img width="150" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/d87af0d8-ee45-48a0-bccd-cacc2b854e9a">      


### 2. 소개팅 관련 기능 
#### 소개 상대 매칭 
- 사용자가 입력한 정보을 기반으로 ,하루 2명의 소개팅 상대 소개
- 새벽 2시마다 업데이트 소개상대 업데이트
- 마이페이지에서 "나에게 두근거린 상대" 확인 후 두근 버튼 누를 수 있도록 구현 
- 서로 "두근"거린 경우 최종 매칭으로 등록
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/fa67b9fb-a973-4090-9402-ea8145d43cf0">      

 #### 최종 매칭 상대와 1:1 채팅 기능     
- 마이페이지의 최종 매칭 페이지에서 최종 매치 페이지에서 최종 매칭 확인
- 최종 매칭 상대와 1:1 채팅
- <img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/b3222b4a-4aec-4aec-a262-99e9e60163e2">      


### 3. 미팅 관련 기능 
#### 미팅방 개설
- 누구나 HOST가 되어 방 개설 가능
- 2:2, 3:3, 4:4까지 참여 인원을 지정하고 학과,학번,나이 제한 없이 누구나 참여 가능 
- 미팅방 인원 충족시 HOST가 미팅방을 시작
- 미팅방이 시작되면 참여자들의 카카오톡 아이디 공유
- 참여중인 미팅, 내가 만든 미팅, 성사된 미팅 모두 마이페이지에서 확인 가능
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/bc00a6af-5fc5-4941-9bfd-a23ec4fb024f">        

 #### 미팅방 인원 충족 후 성사시 방장에게 카카오톡 아이디 제공 
- 마이페이지의 최종 매칭 페이지에서 최종 매치 페이지에서 최종 매칭 확인 및 카카오톡 친구추가
- 최종 매칭 상대와 1:1 채팅 (예정)
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/b42ccf1e-6f53-4995-9ef0-d1e3cefb6ce6">       

## 👩‍💻 Skills
## 외부 대학생 인증 API 비동기 처리 
회원 가입을 진행하는 순서는 다음과 같습니다.
- 데이터베이스를 조회하여 사용자가 존재하는 지 먼저 체크합니다.
- 사용자가 존재하지 않으면 데이터베이스의 사용자를 임시 저장합니다.
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/f18c6790-6217-4e44-ac1b-8128e1ff0cae">

#### 비동기 처리 적용한 이유
1. **성능 개선** 사용자 인증 메일 작업이 처리되는 동안 다른 작업 수행 가능하도록 하여 사용자 경험을 향상시킵니다. 
2. **시스템 안정** 별도의 스레드에서 작업을 수행하기 때문에 작업 실패 시 전체 시스템에 큰 영향이 없습니다. 

 **AsyncConifg**
```java
@EnableAsync
@Configuration
public class AsyncConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("UNI-VERIFICATION");
        executor.initialize();
        return executor;
    }

}
```
위와 같은 쓰레드풀 설정을 진행하여 기본 요청 수, 대기 queue 사이즈, queue 사이즈 초과 시 추가할 쓰레드 개수 등을 지정합니다. 

 **CertService**
```java
@Async
public CompletableFuture<Boolean> sendVerificationEmailAsync(String email, String uniName) {
    try {
        boolean isSend = startEmailVerification(email, uniName);
        return CompletableFuture.completedFuture(isSend);
    } catch (IOException e) {
        // 예외 처리
        return CompletableFuture.completedFuture(false);
    }
}
```
```java
certService.sendVerificationEmailAsync(user.getEmail(), user.getUniName())
    .exceptionally(ex -> {
        log.error("Email verification failed to send for: {}", user.getEmail(), ex);
        return false;
    })
    .thenAccept(isSend -> {
        if (isSend) {
            log.info("Email verification started for: {}", user.getEmail());
        }
    });
```
이메일 발송을 비동기적으로 처리. 이제 이메일 발송은 회원가입 요청과 별개로 백그라운드에서 실행됩니다.

## Spring의 Stomp를 이용한 1:1 채팅 구현
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/cdc77bdc-b8b2-4a92-8447-204634c17427">

#### 동작 원리
1. 클라이언트(Sender)가 메시지를 보내면 stomp통신으로 서버에 메세지가 전달됩니다.
2. Controller의 @MessageMapping에 의해 메시지를 받습니다.
3. Controller의 @SendTo으로 특정 topic을(/1) 구독(/room)하는 클라이언트에게 메세지를 보냅니다. (구독은 /room으로 보면 되고 특정 topic은 채팅 방 id인 /1로 보면 된다. →/room/1}
   
 **WebSocketConfig**
```java
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                 .setAllowedOriginPatterns("*")
                 .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/room"); 
        config.setApplicationDestinationPrefixes("/send"); 
    }


}

```
 **ChatController**
```java
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/{roomId}")
    @SendTo("/room/{roomId}")   
    public ChatMessage test(@DestinationVariable Long roomId, ChatMessage message) {

        //채팅 저장
        Chat chat = chatService.createChat(roomId, message.getSender(), message.getMessage());
        return ChatMessage.builder()
                .roomId(roomId)
                .sender(chat.getSender())
                .message(chat.getMessage())
                .sendDate(LocalDateTime.now())
                .build();
    }

}
```

## Spring Batch + Spring Scheduler 적용을 통한 대용량 데이터 처리 
#### Spring Batch 적용 이유 
1. **예외 사항과 비정상 동작에 대한 방어** Match(소개 상대)를 계산하는 로직의 경우 모든 사용자들에 대해 수행이 되는데, 전체 사용자가 300명이라고 할 때, 290번째 데이터까지는 잘 진행되다가 291번째에 오류가 발생하여 배치 작업이 실패로 돌아간 경우, 다시 1번째 작업부터 시작하여야 하는데 이것의 매우 비효율적입니다.Spring 배치는 이런 상황에서 정확하게 실패가 발생한 290번째부터 다시 배치 작업을 수행하도록 합니다.
2. **비즈니스 로직과 분리** @Scheduled에너테이션이 붙은 메서드는 스프링의 TaskScheduler에 의해 관리되며, 이는 별도의 스레드에서 실행됩니다. finalMatchJobScheduled() 메서드는 스케줄링에 따라 10분마다 호출되며, 이런 호출이 발생할 때마다 TaskScheduler는 이 메서드를 실행하기 위하여 새로운 스레드를 생성하기 때문에 비즈니스 로직과는 분리됩니다.

<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/d71bc7b0-11e4-4296-9d81-cf900ce9ae49">
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/a9b0a3c5-6700-4350-b512-e813bcad4c72">

**MatchJobConfig**
```java
@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class MatchJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final MatchService matchService;

    @Bean
    public Job matchJob() throws Exception {
        return jobBuilderFactory.get("matchChunkJob")
                .start(matchStep())
                .build();
    }

    @Bean
    @JobScope
    public Step matchStep() throws Exception {
        return stepBuilderFactory.get("step")
                .<User, List<Match>>chunk(2)
                .reader(matchReader())
                .processor(matchProcessor())
                .writer(matchListWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<User> matchReader() throws Exception{
        Map<String,Object> parameterValues = new HashMap<>();
        log.info("ItemReader 실행됨");
        return new JpaPagingItemReaderBuilder<User>()
                .pageSize(2)
                .parameterValues(parameterValues)
                .queryString("SELECT m FROM User m")
                .entityManagerFactory(entityManagerFactory)
                .name("JpaPagingItemReader")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<User, List<Match>> matchProcessor()
    {
        log.info("ItemProcessor 실행됨");
        return new ItemProcessor<User, List<Match>>() {
            @Override
            public List<Match> process(User user) throws Exception
            {
                // 해당 user의 다른 사용자들에 대한 match 점수들 계산해서 얻어낸 match들 return
                return matchService.calculateMatches(user);
            }

        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<List<Match>> matchWriter()
    {

        log.info("ItemWriter 실행됨");
        return new JpaItemWriterBuilder<List<Match>>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public JpaItemListWriter<Match> matchListWriter(){
        JpaItemWriter<Match> writer  = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);

        JpaItemListWriter<Match> jpaItemListWriter = new JpaItemListWriter<>(writer);
        jpaItemListWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemListWriter;

    }

}
```
