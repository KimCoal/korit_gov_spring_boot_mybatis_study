package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.AddUserReqDto;
import com.korit.jpa_study.dto.ApiRespDto;
import com.korit.jpa_study.dto.EditUserReqDto;
import com.korit.jpa_study.entity.User;
import com.korit.jpa_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> addUser(AddUserReqDto addUserReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(addUserReqDto.getUsername());
        if (foundUser.isPresent()) {
            return new ApiRespDto<>("failed", "닉네임 중복", addUserReqDto.getUsername());
        }
        return new ApiRespDto<>("success", "추가 성공", userRepository.save(addUserReqDto.toEntity()));
    }

    public ApiRespDto<?> getAllUser() {
        return new ApiRespDto<>("success" , "조회성공", userRepository.findAll());
    }

    public ApiRespDto<?> getUserByUserId(Integer userId) {
        return new ApiRespDto<>("success", "조회성공", userRepository.findUserByUserId(userId));
    }

    public ApiRespDto<?> getUserByUsername(String username) {
        return new ApiRespDto<>("success", "조회성공", userRepository.findUserByUsername(username));
    }

    public ApiRespDto<?> editUser(EditUserReqDto editUserReqDto) {
        Optional<User> foundUser = userRepository.findUserByUserId(editUserReqDto.getUserId());
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "정보없음", editUserReqDto.getUserId());
        }
        User user = foundUser.get();
        user.setUsername(editUserReqDto.getUsername());
        user.setPassword(editUserReqDto.getPassword());
        user.setUpdateDt(LocalDateTime.now());
        User updateUser = userRepository.save(user);
        return new ApiRespDto<>("success", "조회성공", updateUser);
    }

    public ApiRespDto<?> delUser(Integer userId) {
        Optional<User> foundUser = userRepository.findUserByUserId(userId);
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "정보없음", userId);
        }
        userRepository.deleteById(userId);
        return new ApiRespDto<>("success", "삭제성공", null);
    }
}
