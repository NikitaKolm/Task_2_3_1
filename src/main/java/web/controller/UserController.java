package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.model.UserForm;
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
        UserForm userForm = new UserForm();
        model.addAttribute(userForm);
        model.addAttribute("user", users);
        return "user";
    }

    @PostMapping(value = "/addUser")
    public String addUser(ModelMap model, @ModelAttribute("user") UserForm userForm) {
        userService.saveUser(new User(userForm.getFirstName(), userForm.getLastName(), userForm.getEmail()));
        return "redirect:/user";
    }

    @PostMapping(value = "/deleteUser")
    public String removeUserById(ModelMap model, @ModelAttribute("user") UserForm userForm) {
        userService.removeUserById(userForm.getId());
        return "redirect:/user";
    }

    @GetMapping(value = "/updateUser")
    public String getUserUpdateForm(ModelMap model, @RequestParam Long id) {
        UserForm userForm = new UserForm();
        userForm.setId(id);
        model.addAttribute("userForm", userForm);
        return "updateUser";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(ModelMap model, @ModelAttribute("user") UserForm userForm) {
        userService.updateUser(userForm.getId(), userForm.getFirstName(), userForm.getLastName(), userForm.getEmail());
        return "redirect:/user";
    }
}
