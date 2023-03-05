package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 테스트 자동생성 단축키 -> 컨트롤 쉬프트 T (테스트 클래스 껍따구를 자동으로 만들어줌)
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 테스트 전에 세팅
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 위의 코드를 작성한 이유
    // 맴버 서비스 클래스와 맴버 리포지토리 클래스의 리포지토리의 BD를 연동시키기 위해서
    // 이걸 안하면 각자 다른 DB에 따로 저장해서 테스트 한 꼴이 됨
    // 이렇게 생성자를 만들어서 memberRepository을 memberService에 넣은 형태로 만들면 서비스 <-> 리포지토리 간의 BD 동기화
    // 결론: 맴버 리포지토리의 DB만 사용하게 하려고, 이를 DI(의존성 주입)이라고 함

    @AfterEach // 테스트가 끝나고 매선 실행되는 어노테이션
    public void afterEach(){

        //테스트 하나 끝날때마다 store를 비우게 됨 =  매번 새로운 테스트가 된다
        memberRepository.clearStore();

    }


    @Test
    void 회원가입() { // 회원가입 테스트 // 테스트 코드는 한글로 적어도 문제가 없다! 더 알아보기 쉬워지니까 좋음

        // given ~ 뭔가가 주어지고
        Member member = new Member();
        member.setName("spring");

        // when ~ 햇을 때
        Long saveId = memberService.join(member);

        //then ~ 이캐캐 결과가 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){

        // given ~ 뭔가가 주어지고
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when ~ 햇을 때
        memberService.join(member1);
        // 이름이 spring으로 같은 사용자를 넣었을 때 예외가 터지면 성공
        assertThrows(IllegalStateException.class, () -> memberService.join(member2) );
        // 해당 코드는 예외가 터질테니 테스트는 성공으로 나옴 (실제로는 회원가입이 안된것을 성공시켯다!)


        /*
        try{
            memberService.join(member2); // 이름이 같으므로 원래 여기서 예외 터져야함
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e){
            // 예외로 지정한 텍스트와 같은 경우 패스, 아닌경우 노패스
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1");

        }
        */



    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}