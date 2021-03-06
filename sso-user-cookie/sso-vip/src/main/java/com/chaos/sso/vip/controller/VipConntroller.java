package com.chaos.sso.vip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/vip")
public class VipConntroller {

    /**
     * 登出操作
     * @return
     */
    @GetMapping("/doLoginout")
    public String doLoginout(@CookieValue(required = false,value ="TOKEN") Cookie cookie , HttpServletResponse response, HttpSession session) {
        //删除cookie，登出的时候cookie肯定不是null
        if (cookie!=null) {
            String token = cookie.getValue();
            cookie.setDomain("codeshop.com");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            Object loginUser = session.getAttribute("loginUser");
            if (loginUser != null) {
                //销毁session
                session.invalidate();
            }
            return "index";
        }
        return "index";
    }
}
