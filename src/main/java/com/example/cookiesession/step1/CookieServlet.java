package com.example.cookiesession.step1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        readCookies(cookies);
        // 쿠키를 보내려면
        resp.addCookie(new Cookie("firstCookie", "Hello_Cookie"));
        // 쿠키를 다양하게 만들어보자
        Cookie cookie = makeCookie();
        resp.addCookie(cookie);
        req.getRequestDispatcher("/WEB-INF/views/step01/cookie.jsp").forward(req, resp);
    }

    private Cookie makeCookie() {
        Cookie cookie = new Cookie("customCookie", "haha");
        // 1. path
        // 기본값은 cookie.setPath("/");
        // -> 다른 페이지에서도 볼 수 있다
//        cookie.setPath("/cookie"); // 이 경로에만 읽을 수 있게
        // 2. 지속시간
//        cookie.setMaxAge(-1); // 기본값
        // 세션 쿠키 -> 현재 브라우저가 켜 있는 동안
//        cookie.setMaxAge(16); // 16초 동안 유지
        // 0보다 큰 양수는 초. 60 * 60 * 24 하루. 86400
        cookie.setMaxAge(60 * 60 * 24); // 하루 유지
//        cookie.setMaxAge(86400);
        return cookie;
    }

    private void readCookies(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            System.out.println("cookie = " + cookie);
            System.out.println("cookie.getName() = " + cookie.getName()); // ***
            System.out.println("cookie.getValue() = " + cookie.getValue()); // ***
            System.out.println("cookie.getPath() = " + cookie.getPath());
            System.out.println("cookie.getDomain() = " + cookie.getDomain());
            System.out.println("cookie.getMaxAge() = " + cookie.getMaxAge());
            System.out.println("cookie.getSecure() = " + cookie.getSecure());
        }
    }
}
