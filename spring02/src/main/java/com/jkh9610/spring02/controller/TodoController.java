package com.jkh9610.spring02.controller;

import java.util.List;

import com.jkh9610.spring02.domain.Todo;
import com.jkh9610.spring02.service.TodoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;

@Controller
public class TodoController {
    
    @Resource
    TodoService todoService;

    // localhost:8091/todos 요청하면 실행되는
    @GetMapping("/todos")
    public String getTodos(Model model) throws Exception{
        List<Todo> allTodos = todoService.getTodos();
        model.addAttribute("todoList", allTodos); // view에 model 로 todoList 전달
        return "todolist";
    }

    // RESTful URL  URL 주소를 간단하게 정리해줌
    @GetMapping("/todo/{tno}")
    @ResponseBody
    public Todo getTodo(@PathVariable("tno") int tno) throws Exception {
        return todoService.getTodo(tno);
    }
}