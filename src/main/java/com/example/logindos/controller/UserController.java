package com.example.logindos.controller;


import com.example.logindos.model.Role;
import com.example.logindos.model.UserSec;
import com.example.logindos.service.IRoleService;
import com.example.logindos.service.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping()
    public ResponseEntity<List<UserSec>> getAllUsers(){

        List<UserSec> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id){
        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec){
        Set<Role> roleList = new HashSet<Role>();
        Role readRole;

        //encriptamos contraseña
        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));

        for(Role role : userSec.getRolesList()){
            readRole = (Role) roleService.findById(role.getId()).orElse(null);
            if( readRole != null){
                //si encuentro guardo en la lista
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()){
            userSec.setRolesList(roleList);

            UserSec newUser = userService.save(userSec);
            return  ResponseEntity.ok(newUser);
        }

        return null;

    }
}
