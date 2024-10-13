package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/", "/index"})
public class IndexController {

    private final TaskService taskService;

    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String getIndexPage(Model model) {
        var tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/completed")
    public String getCompleted(Model model) {
        var tasks = taskService.findByDone(true);
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        var tasks = taskService.findByDone(false);
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/one/{id}")
    public String getOnePage(@PathVariable int id, Model model) {
        var task = taskService.findById(id);
        model.addAttribute("task", task);
        return "page/one";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "page/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model) {
        taskService.save(task);
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable int id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "page/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable int id) {
        taskService.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable int id) {
        Task task = taskService.findById(id);
        task.setDone(true);
        taskService.update(task);
        return "redirect:/index";
    }
}
