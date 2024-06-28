package com.jkh9610.backboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {
    private final JavaMailSender javaMailSender;
    // private final PasswordEncoder passwordEncoder;

    // 메일에서 초기화 할 화면으로 이동 URL
    private String resetPassUrl = "http://localhost:8080/member/reset-password"; 

    @Value("${spring.mail.username}")    
    private String from;

    public void sendmail(String to, String subject, String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();   // MIME type 설정

        try {
            // MimeMessageHelper로 MimeMessage구성, 이메일에 작성되는 글은 UTF-8로 생성
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,false,"UTF-8");
            // 이메일 수신자 설정
            mmh.setTo(to);
            // 이메일 제목 설정
            mmh.setSubject(subject);
            // 이메일 내용 설정
            mmh.setText(message);
            // 이메일 발송자 설정
            mmh.setFrom(new InternetAddress(from));
            // 이메일 발송
            javaMailSender.send(mimeMessage);
            
        } catch (MessagingException e) {  
            throw new RuntimeException(e);
        }
    }

    // 패스워드 초기화 메일 전송 메서드
    public Boolean sendResetPasswordEmail(String email) {
        String subject = "";
        String message = "";

        try{
            sendMail(email, subject, message);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
