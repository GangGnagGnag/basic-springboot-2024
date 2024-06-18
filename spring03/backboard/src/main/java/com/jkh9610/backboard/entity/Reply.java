package com.jkh9610.backboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long rno;

    @Column(name="title", length = 1000)
    private String content;

    @CreatedDate
    @Column(name = "createDate", updatable = false)
    private LocalDateTime createDate; // 글 생성일

    // 중요!! ERD로 DB를 설계하지 않고 엔터티 클래스로 관계를 형성하려면 반드시 사용
    // board랑 reply랑 관계를 맺는거
    // RelationShip 다대일 설정
    @ManyToOne
    private Board board;
}