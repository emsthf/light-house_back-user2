package com.jo.user2.service;

import com.jo.user2.model.User;
import com.jo.user2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        if(user == null || user.getEmail() == null) {
            throw new RuntimeException("Invalid argument");
        }
        final String email = user.getEmail();
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User update(User user) {
        user = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNum(user.getPhoneNum())
                .build();
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // 유저 검색
    @Transactional
    @Override
    public List<User> searchUsers(String keyword) {
        log.info("### 유저 검색 시작 ###");
        List<User> users = userRepository.findByEmailContaining(keyword);
        log.info("### 유저 레포지토리에서 키워드로 서칭 => 리턴 유저 리스트 ###");
//        List<User> userList = new ArrayList<>();
//
//        if (!users.isEmpty()) {
//            for (User user : users) {
//                userList.add(user);
//            }
//        }
        return users;
    }

//    private User convertEntityToDto(UserDto userDto){
//        return User.builder()
//                .id(userDto.getId())
//                .email(userDto.getEmail())
//                .build();
//    }
}
