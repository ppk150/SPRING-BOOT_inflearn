package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입 메서드
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }


    // 중복 회원 검증하는 메서드
    private void validateDuplicateMember(Member member){

        //같은 이름이 있는 중복회원은 걸러내야함 -가입이 안되어야 함

        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // 맴버 값이 있으면 (맴버가 이미 존재하면)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll(); // 이미 리포지토리에 만들어놓은 findAll사용, list로 반환됨

    }

    // 회원 아이디로 한명만 가죠오기
    public  Optional<Member> findOne(Long memberid){
        return memberRepository.findById(memberid);
    }





}
