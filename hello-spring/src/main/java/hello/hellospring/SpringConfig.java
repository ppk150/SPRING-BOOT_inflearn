package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // 맴버 서비스를 빈으로 넣음
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean// 맴버 리포지토리를 빈으로 넣음
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    //맴버 이포지토리 -> 맴버 서비스 -> 맴버 컨트롤러로 엮여서 작동함
}
