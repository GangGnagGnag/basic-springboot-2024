package com.jkh9610.backboard.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberForm {

    @Size(min = 4, max = 40)
    @NotBlank(message = "사용자 이름은 필수입니다!")
    private String username;

    @NotBlank(message = "이메일은 필수입니다!")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다!")
    private String password1;   // 패스워드 1과 2를생성해서 한번 더 확인 
    
    @NotBlank(message = "비밀번호 확인은 필수입니다!")
    private String password2; 
}
