package com.jkh9610.backboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jkh9610.backboard.entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
    Optional<Category>findByTitle(String title);    //select * from Category where title = :title
}
