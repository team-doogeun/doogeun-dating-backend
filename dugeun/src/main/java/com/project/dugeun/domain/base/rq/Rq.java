package com.project.dugeun.domain.base.rq;

import com.project.dugeun.domain.signin.application.SigninService;
import com.project.dugeun.domain.user.dao.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Component
@RequestScope
public class Rq {

    private final UserRepository userRepository;

    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;
    private final User user;
    private com.project.dugeun.domain.user.domain.User member = null; // 레이지 로딩, 처음부터 넣지 않고, 요청이 들어올 때 넣는다.

    public Rq(UserRepository userRepository, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        this.userRepository = userRepository;
        this.req = req;
        this.resp = resp;
        this.session = session;

        // 현재 로그인한 회원의 인증정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            this.user = (User) authentication.getPrincipal();
        } else {
            this.user = null;
        }
    }

    // 로그인 되어 있는지 체크
    public boolean isLogin() {
        return user != null;
    }

    // 로그아웃 되어 있는지 체크
    public boolean isLogout() {
        return !isLogin();
    }

    // 로그인 된 회원의 객체
    public com.project.dugeun.domain.user.domain.User getMember() {
        if (isLogout()) return null;

        // 데이터가 없는지 체크
        if (member == null) {
            member = userRepository.findByUserId(user.getUsername());
        }

        return member;
    }

    // 뒤로가기 + 메세지
    public String historyBack(String msg) {
        String referer = req.getHeader("referer");
        String key = "historyBackErrorMsg___" + referer;
        req.setAttribute("localStorageKeyAboutHistoryBackErrorMsg", key);
        req.setAttribute("historyBackErrorMsg", msg);
        return "common/js";
    }


}
