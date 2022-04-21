package com.jo.user2.controller;

import com.jo.user2.model.User;
import com.jo.user2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public void signUp(@RequestBody User user) {
        try {
            User user1 = User.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            User registeredUser = userService.create(user);
        } catch (Exception e) {
            log.error(e.getMessage() + " signup fail!!!");
        }
    }

    // 로그인
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User user1 = userService.getUser(user.getEmail(), user.getPassword());
        if(user1 != null) {
            return user1;
        } else {
            User fakeUser = new User(0L, "", "", "", "");
            return fakeUser;
        }
    }

    // 유저 조회
    @GetMapping("/user/get/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    // 모든 회원 정보 조회(관리자)
    @GetMapping("/user/getAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    // 회원 정보 수정
    @PutMapping("/user/edit")
    public User userEdit(@RequestBody User user) {
        return userService.update(user);
    }

    // 회원 검색
    @GetMapping("/user/search")
    public List<User> search(@RequestParam(value = "keyword") String keyword, Model model) {
        log.info("@@@ 유저 검색 api 작동 @@@");
        List<User> userList = userService.searchUsers(keyword);
        log.info("@@@ 유저 서비스 작동 완료 @@@");
        model.addAttribute("userList", userList);
        log.info("@@ addAttribute => 리턴 유저 리스트 @@");

        return userList;
    }

    // 회원 탈퇴
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
