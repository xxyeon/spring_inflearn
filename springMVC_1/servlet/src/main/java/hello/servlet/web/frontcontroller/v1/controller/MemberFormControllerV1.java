package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //servletmvc 코드 그대로 복붙
        String viewPath = "/WEB-INF/views/new-form.jsp"; // 이동할 JSP 파일 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);// 컨트롤러에서 뷰로 이동할 때 사용
        dispatcher.forward(request, response); // 서블릿에서 JSP 호출. 서버 내부에서 다시 호출 발생
    }
}
