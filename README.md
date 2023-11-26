
# 두근프로젝트

## 서비스 소개
<img width="800" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/10598f67-6d43-4ae1-9d0f-050456590c70">     


**‘두근’,** 팀명이자 프로젝트명으로 단어 자체가 가지는 의미처럼 저희 서비스를 이용하며 학생들이 서로 두근 거리며 즐거운 경험을 하였으면 하는 마음으로 이번 프로젝트를 기획했습니다.

### What ? 
1. **하루에 한 번 내 이상형 2명 소개 받기 !** 사용자가 입력한 정보에 따라, 사용자의 이상형을 기반으로 이상형에 가까운 소개를 소개해줍니다.
2. **자유롭게 미팅방 생성 및 참가 가능 !** 2:2, 3:3, 4:4까지 누구나 호스트가 되어 자유롭게 방을 개설할 수 있고 참여하여 많은 실제 미팅 분위기를 만들 수 있습니다 
 
## 개발기간
- 2023년 3월 ~ 2023년 09월

## 팀원  
- 심예설 (백엔드 -회원가입 및 정보입력, 소개팅 관련 기능 구현)
- 이승민 (백엔드 -미팅 관련 기능 구현, 서버 배포)


## 🔧 Tools 
- Spring boot
- JPA
- AWS RDS(MySql)
- AWS S3
- Google Cloud Platform(배포)

## 테이블 (다이어그램)  
<img width="478" alt="스크린샷 2023-09-08 오후 4 38 08" src="https://github.com/team-doogeun/doogeun-dating-backend/assets/89733207/d9531e44-57e6-4691-a1a7-be0e5108c3e4">     

## API명세 
아래 페이지에서 API명세서를 확인할 수 있습니다.
https://caramel-beast-94d.notion.site/api-72d47c6a90fb4264a220edd1e62c6c4d?pvs=4

## 사이트


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
- 동영상 시연 🚀 -> https://www.youtube.com/watch?v=4c_djB9Qxvs 

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

   
