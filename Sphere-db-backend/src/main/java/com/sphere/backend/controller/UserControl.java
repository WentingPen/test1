package com.sphere.backend.controller;
import com.sphere.backend.models.User;
import com.sphere.backend.models.UpdateRequest;
import com.sphere.backend.service.DatabaseManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserControl {
    @Autowired
    private DatabaseManagement UserManage;

    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UpdateRequest request) {
        String result = UserManage.updateUser( request.getUpdates() ,request.getCondition(),request.getDb());
        if (result.equals("Database does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Database does not exist"));
        } else if (result.startsWith("SQL Error")) {
            return ResponseEntity.badRequest().body(Map.of("error", result));
        }else {
            return ResponseEntity.ok(Map.of("message", result));
        }

    }
    @PostMapping("/add")
    public ResponseEntity <Map<String, Object>> addUser(@RequestBody User userDTO){
        String result = UserManage.addUser(userDTO.getName(), userDTO.getEmail(), userDTO.getAge(), userDTO.getDb());
        return ResponseEntity.ok(Map.of("message",result,"name",userDTO.getName()));
    }
    @PostMapping("/del")
    public ResponseEntity<Map<String,Object>>delUser(@RequestBody User userDTO){
        String result = UserManage.delUser(userDTO.getName(), userDTO.getDb());
        if (result.equals("Database does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Database does not exist"));
        } else if (result.startsWith("SQL Error")) {
            return ResponseEntity.badRequest().body(Map.of("error", result));
        } else {
            return ResponseEntity.ok(Map.of("message", result));
        }
    }
}
