package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * v1에서 컨트롤러는 다 다른 파일에 나눠서 작성했는데 v2에서 그걸 통합
 */
@Controller
@RequestMapping("/springmvc/v2/members") //요청 url에 중복된 부분 작성
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/new-form") // 어댑터: 해당 url로 요청이 오면 이 어댑터 메소드 실행
    public ModelAndView newForm() {
        return new ModelAndView("new-form"); //뷰 리졸버에서 render 된다.
        //어댑터: ModelAndView로 변환해서 dispatcherServlet으로 넘겨주기 위함.
    }

    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll(); // 저장된 회원 목록 모두 가져오기

        ModelAndView mv = new ModelAndView("members");
        //mv.getModel().put("members", members);// ModelView에 Map에 회원 목록 저장
        mv.addObject("members", members);

        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member", member); //회원 가입 정보 ModelView 의 Map에 저장
        mv.addObject("member", member); // 위 코드를 간단하게 addObject 활용
        return mv;
    }
}
