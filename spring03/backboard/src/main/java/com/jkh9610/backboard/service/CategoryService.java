package com.jkh9610.backboard.service;

import java.time.*;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jkh9610.backboard.common.NotFoundException;
import com.jkh9610.backboard.entity.Category;
import com.jkh9610.backboard.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository; // bean으로 생성
    

    // 카테고리를 생성하는 메서드ㄴ
    public Category setCategory(String title){
        Category cate = new Category();
        cate.setTitle(title);
        cate.setCreateDate(LocalDateTime.now());

        Category category = this.categoryRepository.save(cate);
        return category;
    }

    // free, qna, notice
    public Category getCategory(String title) {
        Optional<Category> cate = this.categoryRepository.findByTitle(title);

        if(cate.isEmpty()) { // free나 qna 타이틀의 카테고리 데이터가 없으면
            cate = Optional.ofNullable(setCategory(title)); //테이블에 해당 카테고리를 생성
        }

        if(cate.isPresent())
            return cate.get(); // Category 리턴
        else
            throw new NotFoundException("Category not found");
    }
}
