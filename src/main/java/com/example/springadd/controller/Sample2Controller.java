package com.example.springadd.controller;

import com.example.springadd.test.TestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sample2")
public class Sample2Controller {

    // 일반 파라미터 → 일반 변수로 받음 → @RequestParam
        // url: .../sample2/ex01?name=pika&age=10
        // form: ...name=pika&age=10
            // .. 로 요청했을 경우:
    @GetMapping("/ex01") // @RequestParam은 생략 가능(파라미터명이 동일할 경우) (단, 간헐적으로 오류 발생하므로 가능한 작성 권장)
    public String ex01(@RequestParam(name="name") String name, @RequestParam(name="age") Integer age) {
        return "ex01";
    }

    // 일반 파라미터 → 자바 객체로 받음 → @ModelAttribute
        // url: .../sample2/ex02?name=pika&age=10
        // form: ...name=pika&age=10
            // .. 로 요청했을 경우:
    @GetMapping("ex02")
    public String ex02(@ModelAttribute TestDTO testDTO) {
        return "ex02";
    }

    // 메서드 수준에서 @ModelA. 사용...
    @ModelAttribute
    public TestDTO modelObject() {
        TestDTO testDTO = new TestDTO();
        testDTO.setName("test");
        testDTO.setAge(10);
        return testDTO; // 이 컨트롤러 내의 모든 html에 동일한 데이터(testDTO) 전달
    }

    @GetMapping("/ex03/{data}") // 'data'(라는 문자열)에 담기는 데이터값은 모두 동일
    public String ex03(@PathVariable("data") String data) {
        return "ex03/" + data;
        // return "redirect:/ex03/{data}"; 리다이렉트 시에는 {}로 사용 !
    }

    // JSON으로 데이터가 전송됨: JSON.stringify({name: "pika", age: 10})
    @PostMapping("ex04") // JSON으로 받았으니까 RequestBody 사용
    public String ex04(@RequestBody TestDTO testDTO) {
        return "ex04";
    }

    @PostMapping("/ex05")
    public String ex05(HttpEntity<TestDTO> httpEntity) {
        TestDTO testDTO = httpEntity.getBody();
        return "ex05";
    }

    // ajax 요청 들어옴: 문자열 리턴
    @GetMapping("/ex06")
    @ResponseBody // View 사용 X, 문자열 리턴므로...
    public String ex06() {
        return "ex06";
    }

    // ajax로 요청 들어옴 → 문자열 데이터 리턴
    // 한글깨짐 방지를 위해 헤더정보 포함시켜 응답 - ResponseEntity 사용
    @GetMapping("/ex07")
    public ResponseEntity<List<TestDTO>> ex07() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "text/plain; charset=UTF-8");
//        return new ResponseEntity<>("ex07", headers, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(new ArrayList<>());
    }

    @GetMapping("/ex08")
    public String ex08(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("data1", "qwer");
        redirectAttributes.addFlashAttribute("data2", "asdf");
        return "redirect:/text/ex09";
    }

    @GetMapping("/ex09")
    public String ex09() {
        return "ex09";
    }

}
