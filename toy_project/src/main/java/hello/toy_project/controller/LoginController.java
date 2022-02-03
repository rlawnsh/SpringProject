package hello.toy_project.controller;

import hello.toy_project.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginRepository loginRepository;

    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }
}
