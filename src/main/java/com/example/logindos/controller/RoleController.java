package com.example.logindos.controller;

import com.example.logindos.model.Permission;
import com.example.logindos.model.Role;
import com.example.logindos.service.IPermissionService;
import com.example.logindos.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService iRolService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping()
    public ResponseEntity<List<Role>> getAllRoles(){

        List<Role> roles = iRolService.findAll();
        return ResponseEntity.ok(roles);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        Optional<Role> role = iRolService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        Set<Permission> permiList = new HashSet<Permission>();
        Permission readPermission;

        //Recuperar la lista de Permission/s por si id
        for(Permission per : role.getPermissionsList()) {
            readPermission = (Permission) permissionService.findById(per.getId()).orElse(null);
            if(readPermission!= null){
                permiList.add(readPermission);
            }
        }

        role.setPermissionsList(permiList);
        Role newRole = iRolService.save(role);
        return ResponseEntity.ok(newRole);
    }

}
