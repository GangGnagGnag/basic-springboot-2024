package com.jkh9610.backboard.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jkh9610.backboard.entity.Board;
import com.jkh9610.backboard.repository.BoardRepository;


@SpringBootTest
public class BoardRepositoryTests {

    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void testInsertBoard(){
        Board board1 = new Board(); // 전통적인 객체 생성방식
        board1.setTitle("첫번째 테스트");
        board1.setContent("첫번째 테스트 내용");
        board1.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board1);

        // Builder를 사용한 객체 생성방식
        Board board2 = Board.builder()
               .title("두번째 테스트")
               .content("두번째 테스트 내용")
               .createDate(LocalDateTime.now())
               .build();
        this.boardRepository.save(board2);
        System.out.println("Bord테스트완료");
    }
    @Test
    void testSelectBoard(){
        List<Board> all = this.boardRepository.findAll(); // select * from board
        assertEquals(8, all.size());
        System.out.println(all.size());

        Board bd = all.get(0); // 게시글중 가장 첫번째 값
        assertEquals(1, bd.getBno());  // 첫번째 게시글에 PK 값이 1인지 확인
        System.out.println(bd.getBno());
    }
    @Test
    void testUpdateBoard(){
        Optional<Board> bd = this.boardRepository.findById(1L); // Long 값은 뒤에 L 추가
        assertTrue(bd.isPresent()); // bno 가 1번인 게시글 객체 넘어왔는지 확인
        Board ubd = bd.get(); // 
        ubd.setContent("텍스트로 수정합니다");
        this.boardRepository.save(ubd); // save() id 가 없으면 INSERT, 없으면 UPDATE 쿼리 자동실행
        System.out.println("수정 완료");
    }

    @Test
    void testDeleteBoard(){
        Optional<Board> bd = this.boardRepository.findById(2L);
        assertTrue(bd.isPresent());
        Board dbd = bd.get();
        this.boardRepository.delete(dbd);
        System.out.println("삭제 완료");
    }
}
