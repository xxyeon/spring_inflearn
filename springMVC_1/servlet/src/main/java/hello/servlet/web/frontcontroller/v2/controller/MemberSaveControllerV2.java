package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //servletmvc 코드 복붙
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); //응답 결과는 항상 문자이므로 Integer 로 변환

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member); //request 객채 내부에 저장소에 저장

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
