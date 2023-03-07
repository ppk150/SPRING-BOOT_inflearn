package hello.hellospring.Controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컨트롤러 어노테이션 붙여서 스프링 컨테이너 안의 스프링 빈에 넣는다.
public class MemberController {

    private final MemberService memberService;

//    @Autowired  private final MemberService memberService;
    // 위의 코드는 필드 주입 방식

    @Autowired // 스프링 컨데이너에 등록되어 있는 맴버 서비스랑 연결 시켜준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //생성자에 맴버 서비스를 넣어준다 -> 이를 생성자 주입이라고 한다.

    // 오토 와이어는 스프링 컨테이너에 등록이 되어 있는 상태에만 동작 가능

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){

        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member : " + member.getName());

        memberService.join(member);

        return "redirect:/";

    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members",members); // 맴버 등록된거 리스트에 다 담는거
        return "members/memberList";
    }

}
