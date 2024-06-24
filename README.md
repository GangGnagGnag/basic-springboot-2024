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
              
## 2일차, 3일차
- Oracle 도커로 설치
    - Docker는 virtual machine을 업그레이드한 시스템
    - 윈도우에 서비스 내(services.msc) Oracle 관련 서비스 종료
    - Doker에서 Oracle 이미지 컨테이너를 다운로드 후 실행 
    - Dokver 설치시 오류 Docker Desktop - WSL Update failed
        - Docker Desktop 실행 종료 후
        - Windows 업데이트 실행 최신판
        - [Docker오류](https://github.com/microsoft/WSL/releases), wsl.2.x.x.x64.msi 다운로드 설치 한 뒤 
        - Docker Desktop 재실행
    - Oracle 최신판 설치
    ```shell
    > docker --version
    Docker version 26.1.1, build 4cf5afa
    > docker pull container-registry.oracle.com/database/free:latest
    latest: ...... 설치됨
    ... : Download complete
    > docker images
    REPOSITORY                                    TAG       IMAGE ID       CREATED       SIZE
    container-registry.oracle.com/database/free   latest    7510f8869b04   7 weeks ago   8.7GB
    > docker run -d -p 1521:1521 --name oracle container-registry.oracle.com/database/free
    ....
    > docker logs oracle
    ...
    #########################
    DATABASE IS READY TO USE!
    #########################
    ...
    > docker exec -it oracle bash
    bash-4.4$
    ```

    - Oracle system 사용자 비번  oracle로 설정
    ```shell
    bash-4.4$ ./setPassword.sh oracle(대소문자 구분)
    ```

    - Oracle 접속확인
        - DBeaver 탐색기 > Create > Connection

- Database 설정
    - H2 DB - Spring Boot에서 손쉽게 사용한 Inmemory DB, Oracle, MySQL, SQL Server 과 쉽게 호환
    - Oracle - 운영시 사용할 DB
    - MySQL - Optional 설명할 DB
    - Oracle PKNUSB / pknu_p@ss로 생성
    - 콘솔(도커/ 일반 Oracle)
        ```shell
        > sqlplus system/password
        SQL > select name from v$database;
        // 서비스명 확인 
        // 최신버전에서 사용자 생성시 C## prefix 방지 쿼리
        SQL > ALTER SESSION SET "_ORACLE_SCRIPT"=true;
        // 사용자 생성
        SQL > create user pknusb identified by "pknusb_p@ss";
        // 사용자 권한
        SQL> grant CONNECT, RESOURCE, CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW to pknusb;
        // 사용자 계정 테이블 공간설정, 공간쿼터
        SQL> alter user pknusb default tablespace users;
        ```

        - H2 DB - Spring Boot 에서 손쉽게 사용한 Inmermory DB, Oracle, Mysql, SQLServer 과 쉽게 호환
        - MySQL - Optional 설명할 DB

    - Spring Boot + MyBatis
        - application name : Spring02
        - Spring Boot 3.2.6선택 - 3.3.x 에는 MyBatis 없음
        - Dependency
            - Spring Boot DevTools
            - Lombok
            - Spring Web
            - Thymeleaf
            - oracle Driver
            - Mybatis starter
        - Dependency 중 DB(H2, Oracle, MySQL) 가 선택시  application.properties에 DB설정이 안되면 서버 실행 안됨

        - build.gradle 확인
        - application.properties 추가작성
        ```properties
        spring.application.name=spring02

        ## 포트변경
        server.port = 8091

        ## 로그색상
        spring.output.ansi.enabled=always

        ## 수정사항이 있으면 서버 자동 재빌드 설정
        spring.devtools.livereload.enabled=true
        spring.devtools.restart.enabled=true

        ## 로그레벨 설정
        logging.level.org.springframework=info
        logging.level.org.zerock=debug

        ## Oracle 설정 
        spring.datasource.username=pknusb
        spring.datasource.password=pknu_p@ss
        # spring.datasource.url=jdbc:oracle:thin:@localhost:11521:FREE
        spring.datasource.url=jdbc:oracle:thin:@localhost:11521:FREE
        spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

        ## MyBatis 설정
        ## mapper 폴더 밑에 여러가지 폴더가 내재, 확장자는 .xml 이지만 파일명은 뭐든지
        mybatis.mapper-locations=classpath:mapper/**/*.xml
        mybatis.type-aliases-package=com.jkh9610.spring02.mapper
        ```

        - Mybatis 적용
            - SpringBoot 이전 resources/WEB-INF 위치에 root-context.xml에 DB, Mybatis 설정
            - SpringBoot, application.properties + Config.java로 변경

        - MyBatis개발시 순서
            0. spring.datasource.url=jdbc:oracle:thin:@localhost:11521:FREE, thin뒤 : 이 삭제되어 있었음
            1. Database 테이블 생성
            2. MyBatis 설정 -> /config/MyBatisConfig.java
            3. 테이블과 일치하는 클래스 생성(domain, entity, dto, vo(readonly), etc ...) 생성
                - 데이터 컬럼 _는 Java 클래스는 사용 안함
            4. DB에 데이터를 주고받을 수 있는 클래스 (dao, **mapper**, repository ...)생성
                - 쿼리를 클래스내 작성가능, .xml로 분리 가능
            5. (Model)분리했을 경우 /resources/mapper/클래스.xml 생성, 쿼리 입력
            6. 서비스 인터페이스 /service/*Service.java, 서비스 구현 클래스 /service/*ServiceImpl.java 생성 작성
            7. 사용자가 접근하는 @RestController 컨트롤러 클래스 생성 -> @Controller 변경 가능
            8. (Controller)경우에 따라 @SpringBootApplication 클래스에 sqlSessionFactory 빈을 생성 메서드 작성
            9. (View)/resource/templates/Thymeleaf html 생성, 작성

## 4일차
- Spring Boot JPA + Oracle + Thymeleaf + React
    - JPA -> DB 설계를 하지 않고 엔터티 클래스를 DB로 자동생성 해주는 기술, Query로 만들 필요X
    - H2 -> Oracle, MySQL, SQLServer등과 달리 Immemory DB, 스프링부트 실행되면 같이 실행되는 DB
            개발편의성, 다른 DB로 전환시 아주 편리, 개발하는 동안 사용을 추천
    - Thymeleaf -> JSP의 단점 복잡한 탬플릿형태 + 스파게티코드를 해소해주는 템플릿
    - Bootstrap -> 웹디자인 및 css의 혁신
    - 소셜로그인 -> 구글,카카오,네이버 등등 소셜로 로그인 가능
    - React -> 프론트엔드를 분리, 백엔드 서버와 프론트엔드 서버 따로 관리(통합도 가능)

- Spring Boot JPA 프로젝트 생성
    - 명령 팔레트로 시작, Spring Initializr:Creat a Gradle Project..
    - Spring Boot version -> 3.2.6
    - project language -> Java
    - Group Id -> com.jkh9610
    - Arifact Id -> backboard 
    - packaging type -> jar
    - Java version -> 17
    - Dependency
        1. Spring Boot DevTools
        2. Lombok
        3. Spring web
        4. Thymeleaf
        5. Oracle Driver(later)
        6. H2 Database(later)
        7. Data JPA(later)
    - Spring03폴더 내에서 **Genrate into this folder**

- Spring Boot JPA 프로젝트 개발시작
    1. (설정)build.gradle 디펜던시 확인
    2. (설정)application.properties 기본설정 입력(포트번호, 로그색상, 자동재빌드, 로그레벨)
    3. MVC패턴에 맞춰서 각 기능별로 폴더를 생성(controller, service, entity...)
    4. /controller/MainController.java 생성, 기본 메서드 구현
    5. (설정)application.properties H2, JPA 설정 추가
    6. (설정)웹 서버 실행  http://localhost:8080/h2-console DB 연결 확인

    7. /entity/Board.java 생성 
        - GenerationType 타입
            - AUTO : SpringBoot 에서 자동으로 선택(X)
            - IDENTITY : MySQL, SQLServer
            - SEQUENCE : Oracle()
        - column 이름을 createDate로 만들면 DB 컬럼명이 create_date로 생성
            - 컬럼명에 언버다를 넣지 않으려면 @column(name = "createDate") 처럼 사용
    8. /entity/Reply.java 생성
    9. 두 앤티티간 @OneToMany, @ManyToOne 을 설정
    10. 웹 서버 재시작 후 h2-console에서 테이블 생성 확인
    11. /repository/BoardRepository.java 인터페이스(JpaRepository 상속) 생성
    12. /repository/ReplyRepository.java 인터페이스(JpaRepository 상속) 생성
    13. (설정)application.properties ddl-auto=create -> ddl-auto=update 변경
    13. /test/.../repository/BoardRepositoryTests.java 생성, 테스트 메서드 작성
    14. 테스트 시작 > 웹서버 실행 > h2-console 확인

## 5일차
- Tip 
    - Java Test 중 OpenJDK 64-Bit Server VM warning: Sharing is 빨간색 경고가 뜨면
    - Ctrl + ,(설정) > Java Test Config 검색 > settings.json 편집
    ```json
    "java.test.config": {
        "vmArgs": [
            "-Xshare:off"
        ]
    }
    ```
    -  저장 후 실행

- Spring Boot 프로젝트 오류처리
    - 빌드를 해도 제대로 결과가 반영안되면
    - Local Repository를 모두 삭제 후 새로 커밋
    - 프로젝트 새로 로드 ,초기화

- Spring Boot JPA 프로젝트 개발 계속
    1. jUnit 테스트로 CRUD 확인
    2. /service/BoardService.java 생성 후 getList() 메서드 작성
    3. /controller/boardController.java 생성 후 /board/list 실행할 수 잇ㄴ느 메서드 작성
    4. /templates/board/list.html 생성
        - Thymeleaf 속성
            - th:if="${board != null}"
            - th:each="board : ${boardList}"
            - th:text="${board.title}"
    5. /service/BoardService.java 에 getBoard() 메서드 추가
    6. /controller/BoardController.java에/board/detail/{bno} 실행 메서드 작성
    7. /templates/board/detail html

        <img src="https://raw.githubusercontent.com/GangGnagGnag/basic-springboot-2024/main/images/sp003.png" width="730">

    8. /templates/board/detail.html에 댓글영역 추가
    9. /service/ReplyService.java 생성, 댓글저장 메서드 작성
    10. /controller/ReplyController.java 생성, /reply/create/{bno} 포스트매핑 메서드 작성

    11. Bootstrap 적용 방법
        - 1번 방법: 다운로드 후 프로젝트에 위치
        - 2번 방법: CDN링크를 추가
        - https://www.getBootstrap.com 다운로드 후 압축해제
        - bootstrap.min.css, bootstrap.min.js templates/static에 위치
    12. /templates/board/list.html, detail.html 부트스트랩 적용

        <img src="https://raw.githubusercontent.com/GangGnagGnag/basic-springboot-2024/main/images/sp004.png" width="730">    

## 6일차
- Spring Boot JPA 프로젝트 계발 계속
    1. (설정) build.gradle Thymeleaf 레이아웃 사용을 위한 
    2. /templates/layout.html Thymeleaf로 레이아웃 템플릿 생성
    3. list.html, detail.html 레이아웃 템플릿 적용
    4. /templates/layout.html Bootstrap CDN 적용
    5. /templates/board/list.html 게시글 등록버튼 생성
    6. /templates/board/create.html 게시글 작성 페이지 생성
    7. /controller/BoardController.java create() GetMapping 메서드 작성
    8. /service/BoardService.java setBoard()작성
    9. /controller/BoardController.java create() PostMapping 메서드 작성
    10. (문제) 아무내용도 안적어도 저장됨
    11. (설정) build.gradle 입력값 검증 Spring Boot Validation 디펜던시 추가    
    12. /validation/BoardForm.java 클래스 생성
    13. /controller/BoardFormController.java에 BoardForm을 전달(Get, PostMapping 둘다)
    14. create.html 입력항목 name, id를 th:field로 변경(ex. th:field="*{content}" )
    15. 댓글등록에도 반영. ReplyForm, ReplyController, detail.html 작업(12~14 내용과 유사)
    16. detail.html  경고영역 div는 crate.html에서 복사해서 가져오기
    17. (문제)각 입력창에 공백을 넣었을때 입력되는 문제 @NotEmpty는 스페이스를 허용하기 때문에  @NotBlank를 사용해 주어야한다

        <img src="https://raw.githubusercontent.com/GangGnagGnag/basic-springboot-2024/main/images/sp005.png" width="730">

    18. /templates/layout.html에 네비게이션바(navbar) 추가
    19. 테스트로 대량 데이터 추가

## 7일차
- Spring Boot JPA 프로젝트 계발 계속
    0. 개념
    ```sql
    -- Oracle 전용(11g 이하는 이 쿼리가 동작 안함)
    select b1_0.bno,b1_0.content,b1_0.create_date,b1_0.title
     from board b1_0 offset 0 --0부터 시작해서 페이지 사이즈만큼 증가
     rows fetch first 10 rows only  -- 페이지사이즈
    ```

    1. 페이징(중요!)
        - /repository/BoardRepository.java findAll(pageable) 인터페이스 메서드 작성
        - /service/BoardService.java getList(page) 메서드 작성
        - /controller/BoardController.java list()메서드 수정
        - /template/board/list.html boardList -> paging 변경
        - /template/board/list.html 하단 페이징 버튼 추가, thymeleaf 기능추가
        - /service/BoardService.java getList() 최신순 역정렬로 변경
        - /template/board/list.html에 게시글 번호 수정

         <img src="https://raw.githubusercontent.com/GangGnagGnag/basic-springboot-2024/main/images/sp006.png" width="730">

    2.  /template/board/list.html td 뱃지태그 추가

    3. H2 -> Oracle로 DB변경
        - build.gradle, Oracle 디펜던시 추가
        - application.properties Oracle 관련 설정 추가, H2 설정 주석처리
        - 

    4. 스프링시큐리티
        - (설정) build.gradle 스프링 시큐리티 디펜던시 추가
        - (설정) Gradle재빌드, 서버 실행
        - user / 로그상 UUID (서버실행시 마다 변경) 입력
        - /security/SecurityConfig.java 보안설정 파일 생성, 작성 -> 시큐리티를 다시 풀어주는 일

        - /entity/member.java 추가
        - /repository/MemberRepository.java 인터페이스 생성
        - /service/MemberService.java setMember() 메서드 작성

## 8일차
- Spring Boot JPA 프로젝트 계발 계속
    1. 스프링 시큐리티 계속
        - /security/SecurityConfig.java 에 BCryptPasswordEncoder 를 Bean으로 작업
        - /validation/MemberForm.java 생성
        - /controller/MemberController.java 생성
        - /entity/Member.java에 regDate 추가
        - /service/MemberService.java regdate()부분 추가 작성
        - /templates/member/register.html 생성
        - (설정) Member 테이블에 저장된 화원정보 확인
        - /templates/layout.html에 회원가입 링크 추가
        - /controller/MemberController.java Postmapping register에 중복회원가입 방지 추가
        - /security/MemberRole.java enum 으로 ROLE_ADMIN, ROLE_USER 생성
        - /entity/Member.java role 변수 추가

    2. 로그인 기능
        - /security/SecurityConfig.java 에 login url 설정
        - /templates/layout.html 로그인 링크 수정
        - /templates/member/login.html 생성
        - /repository/MemberRepository.java find* 메서드 추가
        - /controller/MemberController.java login Get/Post 메서드 작정
        - /service/MemberSecurityService.java -  로그인은 post를 사용하지 않고, Spring Security가 지원하는 UserDetailsService 인터페이스 활용
        - /security/SecurityConfig.java 게정관리자 Bean 추가
        - /templates/layout.html 로그인/로그아운 토글 메뉴 추가

    3. 게시글 작성자 추가
        - /entity/Board.java, /entity/Reply/java 에 작성자 변수(속성) 추가
        - /service/MemberService.java getMember() 메서드
        - (TIP)default Exception 으로 예외를 처리하면 메서드 뒤에 항상 throws Exception 을 적어줘야 함
        - /common/NotFountException.java 생성 -> throws Exception 쓰는데 반영
        - /service/ReplyService.java. setReply() 사용자 추가
        - /controller/ReplyController.java 오류나는 setReply() 설정
        - /service/BoardService.java 
        - /controller/BoardController.java setBoard() 사용자 추가
        - /controller/ 작성하는 get/post 메서드에 @PreAuthorize 어노테이션추가
        - /config/SecurityConfig.java @PreAuthorize 동작하도록 설정 추가
        - /templates/board/detail.html 답변 textarea 로그인전, 로그인후로 구분

        - /templates/board/list.html 태그에 작성자 컬럼 추가
        - /templates/board/detail.html 게시글 작성자, 댓글 작성자 표시 추가


        <img src="https://raw.githubusercontent.com/GangGnagGnag/basic-springboot-2024/main/images/sp007.png" width="730">


## 9일차
- Spring Boot JPA 프로젝트 계발 계속
    1. 수정, 삭제 기능
       - /entity/Board, Reply.java 수정일자 필드 추가
       - /templates/board/detail.html 수정, 삭제버튼 추가
         - sec:authorize="isAythenticated()" 없으면 500 에러
       - /controller/BoardController.java modify() 메서드 작성
       - /templates/board/create.html  form th:action을 삭제
       	- crate.html 생성, 수정할 때 모두 사용 
         - get이 /board/create 로 들어가면 post도 같은 URL로 실행되고, /board/modify/{bno}로 페이즈를 들어가면 post도 같은 url로 실행
       - /service/BoardService.java 수정관련된 메서드 추가작성
       - /controller/BoardController.java modify() POST 메서드 작성
         - html에는 BoardForm 객체 값이 들어있음. Controller에 받아서 Board객체 다시 만들어 서비스로 전달
       
		- /service/BoardService.java 삭제관련 메서드 추가
        - /controller/BoardController.java delete() GET 메서드 작성
       
        - /templates/board/detail.html 댓글 수정, 삭제버튼 추가
        - /service/ReplyService.java 수정 삭제관련 메서드 추가
        - /controller/ReplyController.java modify get, post메서드, 삭제 get 메서드 작성
        - /templates/reply/modify.html 생성, 작성

		- /templates/board/detail.html에 게시글, 댓글 수정날짜 표시


    2.  앵커기능
        - 추가, 수정, 삭제 시 이전 자신의 위치로 되돌아가는 기능
        - /template/board/detail.html 댓글 마다 앵커링 추가
        - /controller/ReplyController.java modify() Post매핑, return에 앵커링 추가
        - /service/ReplyService.java 생성메서드 변경
        - /controller/ReplyController.java create Post메서드를 변경

        - /controller/BoardController.java detail() 메서드 수정

    3. 검색 기능
        - /service/BoardService.java search() 메서드 추가
        - /repository/BoardRepository.java findAll()메서드 추가
        - /service/BoardService.java getList() 메서드 추가 생성
        - /controller/boardController.java list() 메서드 추가
        - /templates/board/list.html 검색창 추가, searchForm 폼영역 추가, **페이징영역 수정, javaScript 추가**

    4. 마크다운 적용
        - 마크다운 뷰, 마크다운 에디터



    - 마크다운 적용, 마크다운 에디터추가
    - 검색기능
    - 카테고리 추가(게시판, QnA, 공지사항)
    - 비밀번호 찾기, 비밀번호 변경
    - 조회수 추가

    - 리액트 적용
    - 리액트로 프론트엔드 설정
    - thymeleaf - 리액트로 변경
    - SpringBoot RestAPI 작업

    - AWS 라이트세일
    - 서버 접속 프로그램 설정
    - 웹서버 배포
    - 8080 -> 80서버
    - http -> https 변경