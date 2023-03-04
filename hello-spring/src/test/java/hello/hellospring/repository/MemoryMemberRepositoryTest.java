package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =new MemoryMemberRepository();

    @AfterEach // 테스트가 끝나고 매선 실행되는 어노테이션
    public void afterEach(){

        //테스트 하나 끝날때마다 store를 비우게 됨 =  매번 새로운 테스트가 된다
        repository.clearStore();

    }
    // 왜 번거롭게 store를 비우냐?
    // 테스트는 순서가 고정되어 있지 않음
    // 언제는 findById먼저, 언제는 FindByName먼저 할수 있다는 것이다.
    // 만약 다른거 먼저 실행해서 store에 값이 먼저 들어가 있으면 그 값이 반영될 수 있다는 것이다.
    // 예를들어 findAll부분을 보면 그냥 2개 맴버 넣고 2개 들었느냐 묻는데, 만약 findById먼저 실행되면 이미 하나 들어간 상태에서 2개 넣는 꼴이 된다.
    // 그렇게 되면 store 안의 맴버 수는 3명이 되므로 에러가 난다.
    // 결론 : 이전 테스트 할때의 잔여물들이 다른 테스트에 영향을 끼치지 않게 설계해야 한다.
    // 테스트를 먼저 만들어서 틀을 잡아주는것을 TDD(테스트 주도 개발)이라고 한다.
    // 어디 블럭 끼우기 전에 이 블럭이 적절한지 테스트용 틀을 만든거라 생각하묜 됨

    @Test
    public void save(){
        Member member = new Member();

        member.setName("spring");

        repository.save(member);

        Member result =  repository.findById(member.getId()).get();

        // 넣고자 하는 값 (member) 하고 넣은값 (result) 가 같으면 트루로 나옴
        System.out.println("result = " + (result == member));

        // member의 값이 result으로 예상된다, 만약 맞으면 true, 틀리면 오류가 나온다
        assertThat(result).isEqualTo(member);



    }

    @Test
    public void findByName(){
        // 맴버 하나 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 두번째 맴버 저장
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 결과값에 첫번째 맴버 값을 저장
        Member result =  repository.findByName("spring1").get();

        //결과값이 우리가 찾는 맴버 (맴버 1)과 같은가 판별
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        List<Member> result = repository.findAll();
        // 들어있는 맴버 수가 넣은 맴버와 같느냐 아니냐
        assertThat(result.size()).isEqualTo(2);

    }


}
