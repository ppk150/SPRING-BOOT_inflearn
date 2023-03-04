package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {

    // 회원이 저장소에 저장됨
    Member save(Member member);
    //회원 아이디로 찾기
    Optional<Member> findById(Long id);
    //회원 이름으로 찾기
    Optional<Member> findByName(String name);
    //싹다 찾기
    List<Member> findAll();
}
