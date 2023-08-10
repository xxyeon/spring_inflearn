<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response 는 그냥 사용 가능
    // 자바 코드 작성 구간
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age")); //응답 결과는 항상 문자이므로 Integer 로 변환

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    성공
    <ul>
        <li>id=<%=member.getId()%></li>
        <li>username=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>