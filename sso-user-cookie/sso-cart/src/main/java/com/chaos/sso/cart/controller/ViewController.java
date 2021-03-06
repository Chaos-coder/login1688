package com.chaos.sso.cart.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private RestTemplate restTemplate;

    private final String LOGIN_CHECK_ADDRESS = "http://www.codeshop.com:9000/login/checkToken?token=";

    @GetMapping("/index")
    public String toIndex(@CookieValue(required = false,value = "TOKEN") Cookie cookie, HttpSession session){
        if (cookie!=null) {
            String token = cookie.getValue();
            if (!StringUtils.isEmpty(token)) {
                //发送http请求
                Map user = restTemplate.getForObject(LOGIN_CHECK_ADDRESS + token, Map.class);
                session.setAttribute("loginUser",user);
            }
            return "index";
        }
        session.removeAttribute("loginUser");
        return "index";
    }

}
