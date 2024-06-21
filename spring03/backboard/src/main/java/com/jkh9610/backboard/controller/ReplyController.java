package com.jkh9610.backboard.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkh9610.backboard.entity.Board;
import com.jkh9610.backboard.entity.Member;
import com.jkh9610.backboard.service.BoardService;
import com.jkh9610.backboard.service.MemberService;
import com.jkh9610.backboard.service.ReplyService;
import com.jkh9610.backboard.validation.ReplyForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
@Log4j2
public class ReplyController {
    
    private final ReplyService replyService;
    private final BoardService boardService;
    private final MemberService memberService;  // 작성자 입력위해 추가

    // Principal 객체 추가하면 로그인한 사용자명(Member객체)을 알 수 있음
    @PostMapping("/create/{bno}")
    @PreAuthorize("isAuthenticated()")
    public String create(Model model, @PathVariable("bno") Long bno,
                         @Valid ReplyForm replyForm, BindingResult bindingResult, 
                         Principal principal) throws Exception {

            Board board = this.boardService.getBoard(bno);
            Member writer = this.memberService.getMember(principal.getName()); // 지금 로그인 중인 사용자의 아이디 가져옴
            if(bindingResult.hasErrors()){
                model.addAttribute("board", board);
                return "/board/detail";
            }
            this.replyService.setReply(board, replyForm.getContent(), writer);
            log.info("ReplyController 댓글저장 처리 완료");
            return String.format("redirect:/board/detail/%s", bno);
    }
}
