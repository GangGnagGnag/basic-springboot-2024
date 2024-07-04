package com.jkh9610.backboard.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jkh9610.backboard.dto.Header;
import com.jkh9610.backboard.entity.Member;
import com.jkh9610.backboard.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
@Log4j2
public class RestMemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Header<Member> login(@RequestParam Map<String, String> loginInfo) {
        log.info(String.format("▶▶▶▶▶ React에서 넘어온 정보 : %s", loginInfo.get("username")));

        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        try {
            Member member = this.memberService.getMemberByUsernameAndPassword(username, password);

            if (member != null) {
                Header<Member> result = Header.OK(member);
                return result;
            } else {
                Header<Member> fail = Header.OK("Member Not Found");
                return fail;
            }
        } catch (Exception e) {
            log.catching(e);
            Header<Member> fail = Header.OK("Member Not Found");
            return fail;
        }
    }
}
