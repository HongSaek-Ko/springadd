package com.example.springadd.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/*
    * C     Create;     POST                // form method="post", ajax type: "POST"
    * R     Read;       GET                 // 주소 입력, 버튼 클릭-이동, form method="get", 링크(a 태그, window.location.href 등)
    * U     Update;     PUT, PATCH (POST)   // ajax type: "PUT", type:"PATCH"
    * D     Delete;     DELETE (POST)       // ajax type: "DELETE"
    * */

@Controller
@RequestMapping("/hello")
//@RequiredArgsConstructor // 생성자 자동 생성 - final 또는 @Nonnull 붙은 변수를 매개변수로 갖는(즉, 매개변수가 있는 생성자만 생성) 생성자
//@NoArgsConstructor // 기본생성자 자동 생성 (매개변수 없는 생성자)
@Slf4j
public class HomeController {

    //  private final SampleService sampleService 이렇게 써놓으면,
    //  public SampleController(SampleService sampleService) {
    //      this.sampleService = sampleService;
    //  } 이런 형태의 생성자 자동 생성.


//    @GetMapping("/world")
//    public String helloWorld0() { //....hello/world.
//        return "helloWorld"; // ... 이름의 html 파일(페이지) 요청
//    }

//    @PostMapping("/world")
//    public String helloWorldPost() { // post 시 ... 와 동일한 이름의 주소를 찾고,
//        return "helloWorldPost"; // (뭐든지 데이터 처리 후...) ...이름의 html 파일(페이지) 요청
//    }

    @RequestMapping("/test") // 앞단 주소가 다르므로(Controller 상단 어노테이션에서 RequestMapping) 상관 없음
    public String test() {
        return "test";
    }

    // 요청방식 구분하기
    @RequestMapping(value = "/test1") // 어떤 방식(method)의 요청이든지 이 url로 들어오는 요청은 다 받음
    public String testPost1() {
        return "testPost1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET) // == @GetMapping()
    public String testPost2() {
        return "testPost2";
    }

    @RequestMapping("*") // 모든 url, 모든 요청 다 받음
    public String Exception() {
        return "home";
    }

    @RequestMapping({"/asdf", "/qwer"}) // 여러 개의 url(요청)도 지정 가능
    public String Exception2() {
        return "home";
    }

    @RequestMapping("/?") // 한글자만 요청하면 다 여기로.
    public String Exception3() {
        log.info("빵이애요4");
        return "home";
    }

    @RequestMapping(value = "/java", params = "id") // /hello/java?id 로 넘어오는 건 다 됨
    public String Java() {                           // id="asdf" 이건 ?id=asdf 만 가능... 식.
        return "helloJava";
    }

    // /hello/world?id=java&pw=1234&abc=null ... 으로 GET 요청이 들어온다 쳤을 때,
    @GetMapping("/world")
    public String helloWorld() {
            /*@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "pw", required = false) String pw,
            @RequestParam(value = "abc", defaultValue = "0") int abc*/
        return "helloWorld";
    }

    /* <form action="/member/world" method="post" >
          <input type="text" name="id" />
          <input type="text" name="pw" />
          <input type="checkbox" name="hobby" value="sleep" />
          <input type="checkbox" name="hobby" value="netflix" />
          <input type="checkbox" name="hobby" value="running" />
          <input type="submit" value="전송" />
       </form>

       http 메시지: [ ... id=java&pw=1234&hobby=sleep&hobby=netflix] 로 출력.
     */
    @PostMapping("/world")
    public String helloWorldPost(
            @RequestParam(name="id", required=true) String id,
            @RequestParam(name="pw") String pw) {
        return "helloWorld";
    }

}
