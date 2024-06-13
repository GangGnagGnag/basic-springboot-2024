package com.jkh9610.spring02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jkh9610.spring02.domain.Todo;

@Mapper
public interface TodoMapper {

    List<Todo> selectTodosAll();

    Todo selectTodo(int tno);
}