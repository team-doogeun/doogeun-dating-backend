# 할 일 ( 소개팅 기능 💘 )
- [ ] 점수내는 로직 작성
  - [x] 우선순위에 따른 다른 점수 적용
  - [ ] 주소지의 경우 가까운 주소에 있는 주소에 점수 부여 (현재 같은 구에 사는 경우만 점수 부여)
  - [x] 특정 점수 넘어가면 match(blind_date) 디비에 저장해서 소개해줄 상대들로 저장 
  - [ ] 점수 기준 정하기 
- [x] 동일한 상대들에 대한 매칭은 db에 저장하지 않기 
- [x] 점수가 높은 순으로 상대에게 먼저 소개해주기 
- [x] 성별 (남->여, 여->남)에 따른 소개 
- [ ] 하루에 한번 소개 상대 업데이트 ( 소개 주기 설정&매칭 데이터 일괄 처리 )
  - [x] likeablePerson 디비에서 쌍방 매칭인 경우 찾아서 finalMatch에 저장하는 함수 
  - [ ] spring scheduler 적용 

- [x] 한번 소개한 상대는 다시 소개 안 해주기 
 
- [x] 하루에 2명식 소개
  - [x] 응답으로 2명씩 소개해주기 
- [x] 소개해준 상대에 대해 두근 버튼을 누르면 LikeablePerson 디비에 저장
  - [x] DTO코드 작성하여 좋아요 누른 상대 정보 넘겨주기 
  - [x] LikeablePersonRepository에 저장 
- [ ]finalMatch에 서로 선호 표시한 커플 저장
  - [ ]Match의 두 명의 유저 모두 like 누르면 finalMatch에 저장 

- [x] 특정 점수를 넘는 유저가 없어 소개해 줄 상대가 부족한 경우 처리 
  - [x] 기준 점수를 넘지 못했지만 그래도 가장 높은 매칭 점수를 가지고 있는 상대 소개 
- [ ] 마이페이지 "나를 두근거린 사람 페이지"에 나에게 선호를 보낸 사람 리스트 보여주기 
- [x] 선호 표시하면 likeable_person 디비에 저장 
- [ ] 예외처리
- [ ] 로그인 된 유저만 사용하도록
  - [ ] @PreAuthorize 적용 
  - [ ] SecurityConfig 파일에서 따로 설정 (@PreAuthorize는 따로 써줄 필요 없는걸로 ? )
- [ ] 실제 테스트 더미 데이터 넣어서 테스트 
- [ ] 테스트 코드 작성
- [ ] 제너릭 타입 도입


# 추가적으로 해야 할 일 
- [ ] 매칭 알고리즘 고도화 
- [ ] Spring Scheduler를 통해 데이터를 일괄처리하는 것을 Spring Batch로 변경 ( 일괄처리 하는 도중 예외 및 에러처리, 신뢰성 등의 이유로 )
- [ ] 캐시 사용을 통한 부하 줄이기  

# 접근 방법

< ⭐  엔티티 네이밍 관련 정리 >

- Match : 소개할 상대들에 대한 정보 (매칭 후보들)

~~- MatchReuslt : 소개된 상대들에 대한 정보 ( 두 유저의 좋아요 상관없이 일단 소개된 )~~
- LikeablePerson : 어떤 사람이 어떤 사람한테 좋아요 표시했는지에 대한 정보 

- FinalMatch : 실제 매칭 완료된 정보 ( 두 유저다 좋아요 버튼 눌렀을 때 . 실제 커플 .
  


# 특이 사항
🤔 구현 과정에서 아쉬웠던 점

🤔 궁금했던 점 