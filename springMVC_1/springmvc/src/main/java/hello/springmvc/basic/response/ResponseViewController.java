package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {


    @RequestMapping("/request-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/request-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    @RequestMapping("/response/hello") //v3는 권장하지 않는 방법
    public void responseViewV3(Model model) {
        // 컨트롤러 경로 이름과 뷰의 논리적 이름이 같으면 아무것도 반환하지 않아도 컨트롤러 경로 이름에서 response/hello가 논리적 뷰 이름으로 진행된다.
        model.addAttribute("data", "hello!");
    }
}
