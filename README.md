# Getting Started

### 구현 환경

- Kotlin 1.9 / Target JDK 21
- SpringBoot 3.3.5


### 실행 방법

- 내장 Gradle Wrapper를 이용하여 실행하는 것을 권장합니다. 
- jar 빌드 : `./gradlew bootJar`
- 실행 방법 
  - jar 실행 : `java -jar build/libs/coconut-chip-0.0.1-SNAPSHOT.jar`
  - bootRun 실행 : `./gradlew bootRun`
- 테스트 : `./gradlew test`

### 추가 설명 


- 가정한 사실
  - 브랜드는 각 카테고리마다 최소 1개의 상품은 보유하여야 한다.


- 4개의 서브모듈로 구성되어있습니다.
  - interfaces : HTTP API를 정의한 모듈
  - application : 비즈니스 로직을 처리하는 모듈
  - domain : 도메인 모델을 정의한 모듈
  - infrastructure : 외부 시스템과의 연동을 처리하는 모듈


- 모듈간 의존성은 다음과 같습니다
  - interfaces <- application
  - application <- domain
  - infrastructure <- application, domain
  - domain은 어디에도 의존하지 않습니다.

### 구현되지 못한 부분

- 프론트 페이지

