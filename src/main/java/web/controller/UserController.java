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
        List<User> users = userService.getAllUsers();
        User user = new User();
        model.addAttribute(user);
        model.addAttribute("userList", users);
        return "user";
    }

    @PostMapping(value = "/addUser")
    public String addUser(ModelMap model, @ModelAttribute("user") User user) {
        userService.saveUser(new User(user.getFirstName(), user.getLastName(), user.getEmail()));
        return "redirect:/user";
    }

    @PostMapping(value = "/deleteUser")
    public String removeUserById(ModelMap model, @ModelAttribute("user") User user) {
        userService.removeUserById(user.getId());
        return "redirect:/user";
    }

    @GetMapping(value = "/updateUser")
    public String getUserUpdateForm(ModelMap model, @RequestParam Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(ModelMap model, @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/user";
    }
}
