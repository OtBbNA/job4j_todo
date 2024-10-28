package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        for (String timeId : TimeZone.getAvailableIDs()) {
            if (timeId.contains("Etc/GMT-") || timeId.contains("Etc/GMT+")) {
                System.out.println(TimeZone.getTimeZone(timeId));
            }
        }
        var timeZoneList = Arrays.stream(TimeZone.getAvailableIDs())
                .filter(t -> t.contains("Etc/GMT-") || t.contains("Etc/GMT+"))
                .map(TimeZone::getTimeZone)
                .collect(Collectors.toList());
        timeZoneList.sort((t1, t2) -> {
            int time1 = getTime(t1.getID());
            int time2 = getTime(t2.getID());
            return Integer.compare(time1, time2);
        });
        model.addAttribute("timeZoneList", timeZoneList);
        return "users/register";
    }

    private static int getTime(String id) {
        String rsl = id.replace("Etc/GMT", "");
        return Integer.parseInt(rsl);
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User user) {
        var savedUser = userService.save(user);
        if (savedUser.isEmpty()) {
            model.addAttribute("error", "*почта уже используется");
            return "/users/register";
        }
        return "users/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "users/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        var userOptional = userService.findByEmailAndPassword(user.getLogin(), user.getPassword());
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "*почта или пароль введены неверно");
            return "users/login";
        }
        var session = request.getSession();
        session.setAttribute("user", userOptional.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }
}
