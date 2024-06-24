package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;
import study.data_jpa.entity.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;


    @Autowired
    EntityManager em;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }


    /**
     * 메소드 이름으로 쿼리 생성 (스프링 데이터 jpa 테스트)
     */

    @Test
    public void findByUsernameAndAgeGreaterThan(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);
        List<Member> result =
                memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findHelloBy() {
        memberRepository.findHelloBy();
    }


    /**
     * JPA NamedQuery 테스트
     */
    @Test
    public void namedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);
        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    /**
     * @Query, 리포지토리 메소드에 쿼리 정의하기
     */
    @Test
    public void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        assertThat(result.get(0)).isEqualTo(m1);
    }

    /**
     * @Query, 값, DTO 조회하기
     */
    @Test
    public void findUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }
    }
    @Test
    public void findMemberDto() {

        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }


    //파라미터 바인딩
    @Test
    public void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    /**
     * 반환 타입
     */
    @Test
    public void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        Optional<Member> result = memberRepository.findOptionalByUsername("AAA"); //empty 컬렉션을 반환
        //result != null이렇게 예외 처리하면 안됨. 그냥 반환해도 된다.
//        System.out.print("result = " + result.size());

    }

    /**
     * 스프링 데이터 jpa 페이징과 정렬
     */
    //페이지
    @Test
    public void paging() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3; //0번 부터 3개 0,1,2

        //when
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        Page<Member> page = memberRepository.findByAge(10, pageRequest);

        //페이지를 유지하면서 엔티티를 DTO로 변환하기
        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null)); //dto로 변환후 api로 반환 가능

        //페이지 계산을 다 알아서 해준다. 매우 편리!!
        //then
        List<Member> content = page.getContent();// 조회된 데이터
        assertThat(content.size()).isEqualTo(3);//조회된 데이터 수
        assertThat(page.getTotalElements()).isEqualTo(5);//전체 데이터 수
        assertThat(page.getNumber()).isEqualTo(0);//페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2); //전체 페이지 번호
        assertThat(page.isFirst()).isTrue(); //첫번째 항목인가?
        assertThat(page.hasNext()).isTrue(); //다음 페이지가 있는가?
    }
    //슬라이스
    @Test
    public void slice() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;

        //when
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username")); //슬라이스는 limit 내가 요청한 것보다 1개 더 추가해서 요청됨 -> 페이지 말고 더보기로 보는 형식에사 사용
        Slice<Member> page = memberRepository.findByAge(age, pageRequest);

        //페이지 계산을 다 알아서 해준다. 매우 편리!!
        //then
        List<Member> content = page.getContent();// 조회된 데이터
        assertThat(content.size()).isEqualTo(3);//조회된 데이터 수
//        assertThat(page.getTotalElements()).isEqualTo(5);//전체 데이터 수
        assertThat(page.getNumber()).isEqualTo(0);//페이지 번호
//        assertThat(page.getTotalPages()).isEqualTo(2); //전체 페이지 번호
        assertThat(page.isFirst()).isTrue(); //첫번째 항목인가?
        assertThat(page.hasNext()).isTrue(); //다음 페이지가 있는가?
    }

    /**
     * 스프링 데이터 JPA를 사용한 벌크성 수정 쿼리
     */

    @Test
    public void bulkUpdate() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));
        //when
        int resultCount = memberRepository.bulkAgePlus(20);

     /*   //벌크 연산후 영속성 컨텍스트 데이터 날리기 (@Modifying(clearAutomatically = ture) 로도 설정 가능
        em.flush();
        em.clear();*/

        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0); //영속성 컨텍스트에는 40이라고 남아 있음 하지만 db에는 41로 변경됨. (db와 영속성 컨텍스트의 엔티티 상태가 달라짐)
        // ->벌크 연산 이후에는 영속성 컨텍스트를 다 날려야함.
        System.out.println("member5 = " + member5.getAge());
        //then
        assertThat(resultCount).isEqualTo(3);
    }


    /**
     * @EntityGraph
     */
    @Test
    public void findMemberLazy() {
        //given
        //member1 -> teamA
        //member2 -> teamB
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        memberRepository.save(new Member("member1", 10, teamA));
        memberRepository.save(new Member("member1", 20, teamB));

        em.flush();
        em.clear();

        //when
        //select Member 쿼리만 나감.
        List<Member> members = memberRepository.findEntityGraphByUsername("member1");

        //then
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.tem = " + member.getTeam().getName()); //team데이터 가져오기 위해 쿼리가 나감. team과 member가 lazy로 되어있음.
            // team을 실제로 사용할 때 실제 엔티티를 디비에서 가져오게 되어 있음.
        }
    }

    /**
     * JPA Hint & Lock
     */
    @Test
    public void queryHint() {
        Member member1 = memberRepository.save(new Member("member1", 10));
        em.flush(); //db에 sql 쿼리 날라감. 아직 영속성 컨텍스트에 데이터 남아 있음.
        em.clear(); //영속성 컨텍스트 다 날리기(1차 캐시에서 조회하는걸 막기위해)

        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");

        em.flush(); //변경감지
    }

    @Test
    public void lock() {
        Member member1 = memberRepository.save(new Member("member1", 10));
        em.flush(); //db에 sql 쿼리 날라감. 아직 영속성 컨텍스트에 데이터 남아 있음.
        em.clear(); //영속성 컨텍스트 다 날리기(1차 캐시에서 조회하는걸 막기위해)

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");
    }

}