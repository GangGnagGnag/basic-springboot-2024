# basic-springboot-2024
Java 빅데이터 개발자과정 Spring Boot 학습 리포지토리

## 1일차
- Spring Boot 개요
    - 개발환경, 개발 난이도를 낮추는 작업
    - Servlet > EJB > JSP > Spring(부흥기) > Spring Boot(끝판왕!!)
    - 장점
        - Spring 의 기술을 그대호 사용가능(마이그레이션 간단)
        - JPA를 사용하면 ERD나 DB설계를 하지 않고도 손쉽게 DB 생성이 가능
        - Tomcat Webserver가 내장(따로 설치필요x)
        - 서포트 가능 다수 존재(개발을 쉽게 도와줌)
        - JUnit 테스트, Log4J2 로그도 포함
        - JSP, **Thymeleaf**, Mustache 등 ... 편하게 사용할 수 있음
        - DB 연동이 무지 쉽다 
        - [웹사이트 , 웹서버 확인 사이트](https://sitereport.netcraft.com/)


        - MVC 
            <img src="https://raw.githubusercontent.com/GangGnagGnag/basic-springboot-2024/main/images/sp002.png" width="730">

- Spring boot 개발환경 설정
- Java JDK확인 > 17버전 이상
        - [jdk](https://jdk.java.n0et/archive/)
        - 윈도우 +R > sysdm.cpl > 고급 > 환경변수 중 > 시스템 변수 > JAVA_HOME > JDK21 설정  > path > JAVA_HOME bin 설정

    - Visual studio Code
        - VSCodeUserSetup-x64 아님 설치하지 말것
        - VSCodeSetup으로 설치
        - Extensions > Korean 검색 > Install
        - Extensions > Java검색, Extension Pack for Java 설치 
            - Debugger for Java 등 6개 확장 팩이 같이 설치됨
        - Extensions > Spring 검색, String Extension Pack 설치 
            - Sptring Initializer Java Support 등 3개의 확장팩 같이 설치
        - Extensions > Gradle for Java 검색, 설치 
    - Gradle build toll 설치 고려
        - [Gradle build toll](https://gradle.org/releases/)
    - Oracle latest version Docker 
    
- Spring Boot 프로젝트 생성
    - 메뉴 보기 > 명령 팔레트(ctrl + shift + p)
        - Spring Initializr: Create a Gradle Project
        - specify Spring Boot version : 3.2.6
        - specify Project language : Java
        - Input Group Id : com.jkh9610 (개인적으로 변경할 것)
        - Input Artifact Id : spring01 (대문자 불가)
        - Specify packaging type : Jar
        - Specify Java version : 17
        - choose dependencies : selected 0 dependencies
        - 폴더 선택 Diaglog 팝업 : 원하는 폴더 선택 Generate ... 버튼 클릭
        - 오른쪽하단 팝업에서 Open 버튼 클릭
        - Git 설정 옵션, Language Support for Java(TM) by Red Hat 설정 항상버튼 클릭 

    - Troubleshooting
        1. 프로젝트 생성이 진행되다 Gradle Connection 에러가 뜨면, 
            - Extensions > Gradle for Java 제거
            - VS Code 재시작한뒤 프로젝트 재생성 
        2. Gradle 빌드시 버전 에러로 빌드가 실패하면
            - Gradle build tool 사이트에서 에러에 표시된 버전으로 Gradle bt 다운로드
            - 개발 컴퓨터에 설치
        3. ':compileJava' execution faile...
            - JDK 17 .... error 메세지 
            - Java JDK 잘못된 설치 x86(32bit) x64 혼용 설치
            - eclipse adoptium JDK 17 새로 설치, 시스템 환경설정
                - [eclipse adoptium JDK](https://adoptium.net/temurin/releases/?version=17)
            - build.gradle SpringBoot Framework 버전을 다운 3.3.0 -> 3.1.5
            - VS Code 재시작


    - 프로젝트 생성 후 
        - build.gradle 확인 
        - src/main/resources/application.properties(또는 .yml) 확인
        - src/java/groupid/arifactid/ Java 소스파일 위치, 작업
        - src/main/resources/ 프로젝트설정 파일, 웹 리소스 파일(css, js, hmtl, jsp ...)
        - Spring01Application.java Run | Debug  메뉴
        - Gradle 빌드
            - 터미널에서 .\gradlew.bat 실행
            - Gradle for java(코끼리 아이콘) > Tasks > Build > Build play icon(Run task) 실행
        - Spring Boot Dashboard
            - Apps > Spring01 Run | Debug 중에 하나 아이콘 클릭 서버 실행 
            - 디버그로 실행해야 Hot Code replace 가 동작!! 

                 <img src="https://raw.githubusercontent.com/GangGnagGnag/basic-springboot-2024/main/images/sp001.png" width="350">


        - 브라우저 변경설정
            - 설정(Ctrl + ,) > browser 검색 > Spring>Dashboard Open With 'Internal' -> 'external'로 변경 
            - chrome 을 기본브라우저로 사용 추천
              
## 2일차
- Oracle 도커로 설치
    - 설치되어 있는 Oracle 삭제


- Database 설정
    - H2 DB - Spring Boot에서 손쉽게 사용한 Inmemory DB, Oracle, MySQL, SQL Server 과 쉽게 호환
    - Oracle - 운영시 사용할 DB
    - MySQL - Optional 설명할 DB
    - Oracle PKNUSB / pknu_p@ss로 생성
        - 콘솔
        ```shell
        > sqlplus system/password
        SQL > 
        ```