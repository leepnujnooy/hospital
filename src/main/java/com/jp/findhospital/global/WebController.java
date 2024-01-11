package com.jp.findhospital.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WebController {

    @GetMapping(value = {"/","/search"})
    public String forward() {
        return "forward:/index.html";
    }


}
