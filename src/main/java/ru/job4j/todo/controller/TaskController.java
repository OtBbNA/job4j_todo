package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@RequestMapping({"/", "/index"})
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
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
        if (task.isEmpty()) {
            model.addAttribute("message", "Произошла ошибка при обновлении");
            return "errors/404";
        }
        model.addAttribute("task", task.get());
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
        var task = taskService.findById(id);
        if (task.isEmpty()) {
            model.addAttribute("message", "Произошла ошибка при обновлении");
            return "errors/404";
        }
        model.addAttribute("task", task.get());
        return "page/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
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
