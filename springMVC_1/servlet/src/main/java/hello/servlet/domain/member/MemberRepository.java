package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //id가 하나씩 증가하는 sequence

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
        //싱글톤 만들때는 private 으로 생성자 막아야함
    }

    public Member save(Member member) {
        member.setId((++sequence));
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // ArrayList 에 값을 넣거나 조작해도 store.values 를 건드리지 않기 위해
        return new ArrayList<>(store.values()); //store 에 있는 모든 값들을 꺼내서 새로운 ArrayList 에 넘겨준다.
    }

    public void clearStore() {
        store.clear();
    }
}
