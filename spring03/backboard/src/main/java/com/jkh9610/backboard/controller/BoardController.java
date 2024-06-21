package com.jkh9610.backboard.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkh9610.backboard.entity.Board;
import com.jkh9610.backboard.entity.Member;
import com.jkh9610.backboard.service.BoardService;
import com.jkh9610.backboard.service.MemberService;
import com.jkh9610.backboard.validation.BoardForm;
import com.jkh9610.backboard.validation.ReplyForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;
    private final MemberService memberService;

    // @RequestMapping("/list", method=RequestMethod.GET) // 아래와 동일 기능
    // Model -> controller에 있는 객체를 view 로 보내주는 역할을 하는 객체
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        // List<Board>boardList = this.boardService.getList();
        // model.addAttribute("boardList", boardList); // thymeleaf, mustache, jsp 등 view로 보내는 기능

        Page<Board> paging = this.boardService.getList(page);   // 페이징 보드를 만드는것 
        model.addAttribute("paging", paging);    // 페이징된 보드를 view로 전달


        return "board/list"; // template/board/list.html 렌더링해서 리턴하라
    }

    // 댓글 검증을 추가하려면 매게변수로 ReplyForm을 전달
    @GetMapping("/detail/{bno}")
    public String detail(Model model, @PathVariable("bno") Long bno, ReplyForm replyForm) throws Exception {
        Board board = this.boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "/board/detail";
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(BoardForm boardForm) {
        return "board/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid BoardForm boardForm,
                         BindingResult bindingResult,
                         Principal principal) {
        if(bindingResult.hasErrors()){
            return "board/create"; // 현재 html 그대로 머무르기
        }

        Member writer = this.memberService.getMember(principal.getName()); //현재 로그인 사용자 아이디
        // this.boardService.setBoard(title, content);
        this.boardService.setBoard(boardForm.getTitle(), boardForm.getContent(), writer);
        return "redirect:/board/list";
    }
}
