package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.domain.model.Role;
import ru.itmentor.spring.boot_security.demo.domain.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/admin/edit/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User updatedUser, @RequestParam("role") String roleName, @ModelAttribute User user) {
        Role role = roleService.findRoleByName(roleName);

        user.setRoles(new HashSet<>(Arrays.asList(role)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.updateUser(id, updatedUser);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/add")
    public String createUser(@ModelAttribute User user, @RequestParam("role") String roleName) {

        Role role = roleService.findRoleByName(roleName);

        user.setRoles(new HashSet<>(Arrays.asList(role)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.addUser(user);
        return "redirect:/admin/users";
    }
}
