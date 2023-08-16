package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 컨트롤러 애노테이션 대신 아래 두 개의 애노테이션을 사용해도 매핑 대상이 된다.
//@Component //빈으로 등록
//@RequestMapping //매핑 정보로 인식
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") // 어댑터: 해당 url로 요청이 오면 이 어댑터 메소드 실행
    public ModelAndView process() {
        return new ModelAndView("new-form"); //뷰 리졸버에서 render 된다.
        //어댑터: ModelAndView로 변환해서 dispatcherServlet으로 넘겨주기 위함.
    }
}
