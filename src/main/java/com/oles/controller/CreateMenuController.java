package com.oles.controller;

import com.oles.domain.Resource;
import com.oles.domain.Role;
import com.oles.domain.RoleAssResource;
import com.oles.domain.User;
import com.oles.service.RoleService;
import com.oles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CreateMenuController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public List<Resource> treelist() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User storedUser = userService.findByUsername(username);
        System.out.println("------"+username);
        Role role = roleService.findOne(storedUser.getRole().getId());
        System.out.println("------"+role.getDescription());
        List<RoleAssResource> roleAssResources = role.getRoleAssResource();
        System.out.println("------"+roleAssResources.size());
        List<Resource> resources = new ArrayList<>();
        for (RoleAssResource roleAssResource : roleAssResources) {
           resources.add(roleAssResource.getResource());
        }
        System.out.println("------"+resources.size());
        return resources;
    }
}
