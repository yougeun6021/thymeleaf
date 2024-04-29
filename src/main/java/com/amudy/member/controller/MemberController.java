package com.amudy.member.controller;

import com.amudy.member.dto.SignupDto;
import com.amudy.member.service.MemberService;
import com.amudy.response.resData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<resData<Long>> signup(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(new resData<>(memberService.signUp(signupDto)), HttpStatus.CREATED);
    }
}
