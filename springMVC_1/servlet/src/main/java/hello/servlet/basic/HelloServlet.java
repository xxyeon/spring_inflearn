package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // "/hello"로 오면 이게 실행이된다.
public class HelloServlet extends HttpServlet {
    @Override // 이 서블릿이 호출되면 service 메서드가 실행된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain"); //contentType에 들어감 (헤더 정보에 들어감)
        response.setCharacterEncoding("utf-8"); //contentType에 들어감
        response.getWriter().write("hello " + username); //http 메시지 바디에 데이터가 들어감
    }
}
