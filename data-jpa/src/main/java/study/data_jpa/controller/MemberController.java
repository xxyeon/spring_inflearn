package study.data_jpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;
import study.data_jpa.repository.MemberRepository;

/**
 * 도메인 클래스 컨버터 사용 전
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    /**
     * 도메인 클래스 컨버터 사용 전
     */
    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    /**
     * 도메인 클래스 컨버터 사용 후 -> 권장하지는 않음. 조회용으로만 사용하는게 좋음
     */
    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        //스프링부트에서 memberRepository.findById()이 컨버터 과정을 끝내고 회원 엔티티를 반환 해준다.
        return member.getUsername();
    }



    /**
     * Web 확장 - 페이징과 정렬
     */

    @GetMapping("/members") //yml 파일 말고도 @PageableDefault사용해서 개별 설정도 가능(yml과 디폴트 설정이 있으면 디폴트가 우선권을 가짐)
    public Page<MemberDto> list(@PageableDefault(size = 12, sort = "username", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable); //findAll은 Pageable을 파라미터로 받을 수 있다.
        //DTO로 반환
        Page<MemberDto> map = page.map(MemberDto::new);
        return map;
    }

    //page 1부터 시작하기
    @GetMapping("/members/pageNo1") //yml 파일 말고도 @PageableDefault사용해서 개별 설정도 가능(yml과 디폴트 설정이 있으면 디폴트가 우선권을 가짐)
    public Page<MemberDto> pageNo1(@PageableDefault(size = 12, sort = "username", direction = Sort.Direction.DESC) Pageable pageable) {
        PageRequest request = PageRequest.of(1, 2);
        Page<MemberDto> map = memberRepository.findAll(request).map(MemberDto::new); //Page그대로 반환하면 안되고 한번 감싸서 반환
        return map;
    }
//    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
