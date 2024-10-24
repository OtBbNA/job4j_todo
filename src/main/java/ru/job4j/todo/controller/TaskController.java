package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.category.CategoryService;
import ru.job4j.todo.service.priority.PriorityService;
import ru.job4j.todo.service.task.TaskService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"task"})
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    @GetMapping("/all")
    public String getIndexPage(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        var tasks = taskService.findByUser(user);
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/completed")
    public String getCompleted(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        var tasks = taskService.findByDoneAndUser(true, user);
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/new")
    public String getNew(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        var tasks = taskService.findByDoneAndUser(false, user);
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/one/{id}")
    public String getOnePage(@PathVariable int id, Model model) {
        var task = taskService.findById(id);
        if (task.isEmpty()) {
            model.addAttribute("message", "Произошла ошибка при обновлении");
            return "errors/404";
        }
        model.addAttribute("task", task.get());
        return "page/one";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "page/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model, HttpSession session, @RequestParam(required = false) List<Integer> category) {
        var user = (User) session.getAttribute("user");
        task.setUser(user);
        task.setCategories(new ArrayList<>(categoryService.findAllById(category)));
        taskService.save(task);
        model.addAttribute("user", user);
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable int id, Model model) {
        var task = taskService.findById(id);
        if (task.isEmpty()) {
            model.addAttribute("message", "Произошла ошибка при обновлении");
            return "errors/404";
        }
        model.addAttribute("task", task.get());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("priorities", priorityService.findAll());
        return "page/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model, HttpSession session) {
        task.setUser((User) session.getAttribute("user"));
        if (!taskService.update(task)) {
            model.addAttribute("message", "Произошла ошибка при обновлении");
            return "errors/404";
        }
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable int id, Model model) {
        if (!taskService.deleteById(id)) {
            model.addAttribute("message", "Произошла ошибка при удалении");
            return "errors/404";
        }
        return "redirect:/index";
    }

    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable int id, Model model) {
        if (!taskService.updateDoneToTrue(id)) {
            model.addAttribute("message", "Произошла ошибка при изменении статуса");
            return "errors/404";
        }
        return "redirect:/index";
    }
}
