package com.xiaoer.cloud.oauth;

import com.xiaoer.cloud.basic.json.Response;
import com.xiaoer.cloud.user.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping({"/login"})
    public String login(HttpServletRequest request) {
        return "/login";
    }

    @ResponseBody
    @GetMapping({"/user"})
    public Object user() {
       return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @ResponseBody
    @PostMapping({"/doLogin"})
    public Response<String> doLogin(@RequestBody @Valid LoginForm loginForm, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return Response.success(loginForm.getCallback());
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage(),ex);
            return Response.error("用户名或密码错误");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Response.error("系统异常，请稍后重试.");
        }
    }
}
