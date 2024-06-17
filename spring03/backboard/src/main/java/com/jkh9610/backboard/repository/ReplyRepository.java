package com.jkh9610.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jkh9610.backboard.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
    
}
