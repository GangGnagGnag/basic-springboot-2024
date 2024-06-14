package com.jkh9610.spring02.service;

import java.util.List;

import com.jkh9610.spring02.domain.Todo;

// 인터페이스는 메서드를 사용하겠다는 약속임 
public interface TodoService {
    public List<Todo> getTodos() throws Exception;

    public Todo getTodo(int tno) throws Exception;
}
