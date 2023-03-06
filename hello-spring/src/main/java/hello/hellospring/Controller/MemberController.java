package hello.hellospring.Controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 컨트롤러 어노테이션 붙여서 스프링 컨테이너 안의 스프링 빈에 넣는다.
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 컨데이너에 등록되어 있는 맴버 서비스랑 연결 시켜준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
