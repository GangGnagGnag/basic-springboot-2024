package com.jkh9610.backboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private Long rno;

    private String content;

    private LocalDateTime createDate; // 글생성일

    private LocalDateTime modifyDate;

    private String writer;
}
