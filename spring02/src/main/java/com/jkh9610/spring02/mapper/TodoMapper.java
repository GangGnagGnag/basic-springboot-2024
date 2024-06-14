package com.jkh9610.spring02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jkh9610.spring02.domain.Todo;

@Mapper
public interface TodoMapper {

    List<Todo> selectTodos() throws Exception;
    // 데이터베이스와 직접적으로 접목되어 있기 때문에 select사용

    Todo selectTodo(int tno) throws Exception;
}
