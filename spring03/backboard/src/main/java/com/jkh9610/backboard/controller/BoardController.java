package com.jkh9610.backboard.controller;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkh9610.backboard.entity.Board;
import com.jkh9610.backboard.service.BoardService;
import com.jkh9610.backboard.validation.BoardForm;
import com.jkh9610.backboard.validation.ReplyForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    // @RequestMapping("/list", method=RequestMethod.GET) // 아래와 동일 기능
    // Model -> controller에 있는 객체를 view 로 보내주는 역할을 하는 객체
    @GetMapping("/list")
    public String list(Model model) {
        List<Board>boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList); // thymeleaf, mustache, jsp 등 view로 보내는 기능
        return "board/list"; // template/board/list.html 렌더링해서 리턴하라
    }

    // 댓글 검증을 추가하려면 매게변수로 ReplyForm을 전달
    @GetMapping("/detail/{bno}")
    public String detail(Model model, @PathVariable("bno") Long bno, ReplyForm replyForm) throws Exception {
        Board board = this.boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "/board/detail";
    }
    
    @GetMapping("/create")
    public String create(BoardForm boardForm) {
        return "board/create";
    }

    @PostMapping("/create")
    public String create(@Valid BoardForm boardForm,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "board/create"; // 현재 html 그대로 머무르기
        }

        // this.boardService.setBoard(title, content);
        this.boardService.setBoard(boardForm.getTitle(), boardForm.getContent());
        return "redirect:/board/list";
    }
}
