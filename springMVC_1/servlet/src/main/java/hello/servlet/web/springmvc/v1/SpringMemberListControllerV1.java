package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {

        List<Member> members = memberRepository.findAll(); // 저장된 회원 목록 모두 가져오기

        ModelAndView mv = new ModelAndView("members");
        //mv.getModel().put("members", members);// ModelView에 Map에 회원 목록 저장
        mv.addObject("members", members);

        return mv;
    }
}
