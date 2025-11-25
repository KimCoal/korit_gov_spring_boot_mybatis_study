package com.korit.jpa_study.controller;

import com.korit.jpa_study.dto.AddUserReqDto;
import com.korit.jpa_study.dto.EditUserReqDto;
import com.korit.jpa_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody AddUserReqDto addUserReqDto) {
        return ResponseEntity.ok(userService.addUser(addUserReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUserList() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @GetMapping("/username")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody EditUserReqDto editUserReqDto) {
        return ResponseEntity.ok(userService.editUser(editUserReqDto));
    }

    @PostMapping("/del")
    public ResponseEntity<?> delUser(@RequestParam Integer userId) {
        return ResponseEntity.ok(userService.delUser(userId));
    }

}
