package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 리포지토리 인터페이스를 구현 하는 부분, 실직적인 로직이 들어감
public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {

        member.setId(++sequence); // store 해시맵에 넣기 전에 아이디를 세팅해준다 (1씩 올림)
        store.put(member.getId(),member); // 맴버 아이디를 키로, 맴버를 밸류로 갖게 함

        return member; // 저장되는 맴버 리턴
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        return  store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){ // store(맴버 저장용 해시맵) 비우기
        store.clear();
    }
}
