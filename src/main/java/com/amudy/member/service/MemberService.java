package com.amudy.member.service;

import com.amudy.member.domain.Member;
import com.amudy.member.dto.SignupDto;
import com.amudy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Long signUp(SignupDto signupDto) {
        String email = signupDto.getEmail();
        String password = passwordEncoder.encode(signupDto.getPassword());
        String nickname = signupDto.getNickname();

        Member member = Member.createdNormalMember(email,password,nickname);

        memberRepository.save(member);

        return member.getId();
    }
}
