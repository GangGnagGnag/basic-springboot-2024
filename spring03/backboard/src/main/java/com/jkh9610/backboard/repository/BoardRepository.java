package com.jkh9610.backboard.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 페이징을 위한 네임스페이스
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.jkh9610.backboard.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
    
    // 메서드명만 잘 만들면 쿼리를 생성하지 않고 JPA가 만들어주는 기능
    Optional<Board> findByBno(Long bno);
    // Board findByContent(String content);
    Board findByTitle(String title);
    List<Board>findByTitleLike(String title);

    // 페이징용 JPA 쿼리 자동생성 인터페이스 메서드 작성
    @SuppressWarnings("null") // 경고 메시지 없애주는 어노테이션
    // select b1_0.bno,b1_0.content,b1_0.create_date,b1_0.title from board b1_0 offset 0 rows fetch first 10 rows only  쿼리를 만들어서 실행
    Page<Board> findAll(Pageable pageable);
}
