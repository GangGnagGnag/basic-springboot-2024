package com.jkh9610.backboard.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {
    
    @Size(max = 250)
    // @NotEmpty(message = "제목은 필수 입니다")  // 스페이스 허용됨 쓰면 안됨
    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "내용은 필수 입니다")
    private String content;
}
