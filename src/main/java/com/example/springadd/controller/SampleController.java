package com.example.springadd.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//@Configuration // '설정 관련 클래스' 어노테이션. (설정 관련 클래스 spring bean으로 등록)
//@Component // 'settings, controller, service, repository 모두 아님' 어노테이션.
//@RestController // Controller + ResponseBody. view를 리턴하지 않음
@RequestMapping("/sample")
@Controller // '컨트롤러'로 선언, spring bean으로 등록
public class SampleController {

    // Controller에서 메서드의 기본적인 구조
    @GetMapping("/test") // url.
    public String test(Model model, HttpServletRequest request) {
            // Model: 데이터를 화면에 전달해줄 수 있음
            // H.S.R: 상동.

        // 화면에 데이터 전달. attributeName으로 지정된 문자열에 값 추가. data에 문자열을 넣든, 숫자를 넣든, 객체(DTO)를 넣든... 가능.
        model.addAttribute("data", "모든 타입의 값...");
        request.setAttribute("data", "모든 타입의 값...");

        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("test"); // html 경로
        mnv.addObject("data", "모든 타입의 값..."); // == model.addAttr()
        // return mnv; <<< return "test"와 동일.

        return "test"; // template 내부의 "html 파일명". template, .html 생략.
                        // template 생략(prefix 지정되어있음)
                            // static prefix (JS, CSS, 이미지 등 리소스들)
                        // .html 생략 (suffix 지정되어있음)
    }

    // 화면에서 ajax 요청 (화면 요청 X, 데이터만 요청.)
    @GetMapping("/mypage")
    @ResponseBody // html 응답 X, 데이터 응답 // Co
    public List mypage() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        return list; // return 타입 string으로 설정해놓으면 자동으로 html(view)로 인식
    }
}
