# real-estate

[부동산 프로젝트 만들기]

- 사이트 이름 : WHERESMM (Where is room? 줄임말) 웨얼즘 사이트
- 혼자 살고 있는 1인 가구가 늘어남에 따라 자신만의 공간이 매우 중요해지고 있음
- 작은 평수의 방을 임대하거나 구매할 수 있는 사이트를 만들어봄
- 유행하는 한달살기, 두달살기 등 단기로 임대하는 것도 고려

Front-end 
- react
- tailwind css
- javascript
- react-router-dom

Back-end
- spring-boot 2.7.12
- Gradle
- Java 11
- JPA
- Lombok
- H2
- Mysql

*시연 영상

![demo-project](https://github.com/jmy0201cp/real-estate/assets/78574972/6f7cde55-30d5-47e2-baa4-0f9f94961471)


** 구현한 기능 **
1. 로그인 - jwt 토큰 생성 
2. 회원가입
3. 부동산 목록 보여주기
4. 전체/오피스텔/아파트/빌라 부동산 유형으로 목록 필터
5. 위시리스트 페이지 - 위시리스트 저장/삭제 (로그인 후 가능)
6. 해당 부동산 상세 페이지
7. 문의 댓글 달기 - 로그인 후 가능
8. 댓글 조회/등록/수정/삭제 기능
9. 부동산 위치 표시하기(네이버 지도 API 사용)



** 미구현 기능 *
1. 관리자 페이지
- 부동산 정보 등록
- 댓글 답글달기
2. 로그인하면 등록된 유저의 주소로 자동으로 필터되도록 구현
3. 계약유형(월세,전세,단기임대,매매) 필터 기능 추가
4. 해당 부동산의 여러 이미지파일이 있을 경우 수동 슬라이드 기능

