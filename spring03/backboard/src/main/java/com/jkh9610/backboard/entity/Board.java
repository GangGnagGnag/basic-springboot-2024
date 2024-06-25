package com.jkh9610.backboard.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시판 보드 테이블 엔티티
@Getter
@Setter
@Entity // 테이블화
@Builder // 객체 생성을 간략화
@NoArgsConstructor // 파라미터 없는 기본생성자 자동 생성
@AllArgsConstructor //맴버변수 모두를 파라미터로 가지는 생성자 자동생성 
public class Board {    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 나중에 Oracle로 바꿀거다 라는 의미
    private Long bno;   // PK

    @Column(name="title", length = 250)
    private String title;   // 글제목
    
    @Column(name = "content", length = 4000)
    private String content; // 글내용

    @CreatedDate
    @Column(name = "createDate", updatable = false)
    private LocalDateTime createDate; // 글 생성일

    @LastModifiedDate
    @Column(name = "modifyDate")// updatable = true 가 기본 값이 기때문에 따로 적지 않아도 됨
    private LocalDateTime modifyDate; // 글 수정일 기능추가 (24.06.24추가) 

    // 사용자가 여러개의 게시글을 작성할 수 있다
    @ManyToOne
    private Member writer; 

    // 중요!!! RelationShip 일대다
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)    // 게시글을 지우게 되면 게시글에 관련된 댓글도 지운다는 의미
    private List<Reply> replyList; // 댓글 리스트

    @ManyToOne
    private Category category;  //free, qna 로 구분해서 글생성 가능
}