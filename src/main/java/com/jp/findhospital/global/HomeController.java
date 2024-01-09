package com.jp.findhospital.global;

import com.jp.findhospital.domain.comment.service.CommentService;
import com.jp.findhospital.domain.hospital.service.HospitalService;
import com.jp.findhospital.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final HospitalService hospitalService;
    private final CommentService commentService;
    private final UserService userService;


    @GetMapping("/")
    public String home(){
        return "home";
    }

}
