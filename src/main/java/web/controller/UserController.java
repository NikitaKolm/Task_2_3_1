package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String getUserList(ModelMap model) {
        model.addAttribute(new User());
        model.addAttribute("userList", userService.getAllUsers());
        return "user";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(new User(user.getFirstName(), user.getLastName(), user.getEmail()));
        return "redirect:/user";
    }

    @PostMapping(value = "/deleteUser")
    public String removeUserById(@ModelAttribute("user") User user) {
        userService.removeUserById(user.getId());
        return "redirect:/user";
    }

    @GetMapping(value = "/updateUser")
    public String getUserUpdateForm(ModelMap model, @RequestParam Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/user";
    }
}
