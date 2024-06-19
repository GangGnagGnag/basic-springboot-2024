package com.jkh9610.backboard.validation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyForm {
    
    @NotBlank(message = "내용은 필수 입니다")
    private String content;
}
