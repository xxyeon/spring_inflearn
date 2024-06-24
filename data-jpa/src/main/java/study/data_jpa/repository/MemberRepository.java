package study.data_jpa.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy(); //멤머 데이터 전체 조회

    /**
     * @Query(name = "Member.findByUsername") annotation생략해도 Named 쿼리 호출 가능
     */
    List<Member> findByUsername(@Param("username") String username);
    //@Param("username")을 적어주는 이유: @NamedQuery에 jpql에 username이라고 named 파라미터를 정한 경우

    /**
     * @Query, 리포지토리 메소드에 쿼리 정의하기
     */
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);


    /**
     * @Query, 값, DTO 조회하기
     */
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.data_jpa.dto.MemberDto(m.id, m.username, t.name)" +
            "from Member m join m.team t")
    List<MemberDto> findMemberDto();

    /**
     * 파라미터 바인딩
     */
    @Query("select m from Member m where m.username = :name")
    Member findMembers(@Param("name") String username);

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    /**
     * 반환타입
     */
    List<Member> findListByUsername(String name); //컬렉션 반환

    Member findMemberByUsername(String name); //단건(엔티티)

    Optional<Member> findOptionalByUsername(String name); //단건 Optional

    /**
     * 스프링 데이터 jpa 페이징과 정렬
     */
    @Query(value = "select m from Member m left join fetch m.team t", countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable); //count 쿼리 사용

    /**
     * 스프링 데이터 JPA를 사용한 벌크성 수정 쿼리
     */
    @Modifying(clearAutomatically = true) //벌크성 쿼리 실행 후 영속성 컨텍스트 초기화
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    /**
     * @EntityGraph
     */
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    //공통 메서드 오버라이드
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    //JPQL + 엔티티 그래프
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    //@EntityGraph: 메서드 이름으로 쿼리에 특히 편리하다.
    //@EntityGraph: @NamedEntityGraph를 이용할 수도 있다.
    @EntityGraph(attributePaths = ("team"))
//    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    /**
     * JPA Hint & Lock
     */
    //JPA Hint
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    //쿼리 힌트 Page 추가 예제
    @QueryHints(value = {@QueryHint(name = "org.hibernate.readOnly", value = "true")}, forCounting = true)
    Page<Member> findByUsername(String name, Pageable pageable);

    //Lock
    //jakarta.persistence.LockModeType; -> jpa가 지원
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String name);
}
