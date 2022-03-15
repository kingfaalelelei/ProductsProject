package com.kingfaalelelei.ProductsProject.controller

import com.kingfaalelelei.ProductsProject.entity.Todo
import com.kingfaalelelei.ProductsProject.service.TodoService
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.spi.LoggerContextFactory
import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
public class TodoController {
    private final int ROW_PER_PAGE = 5;

    //Logger logger = org.slf4j.LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private Environment env;

    @Autowired
    private TodoService todoService;

//    @Value("${msg.title}")
//    private String title;

    @GetMapping(value = ["/todos/index"])
    public String index(Model model) {
        model.addAttribute("title", env.getProperty("msg.title"));
        return "todos-index";
    }

    @GetMapping(value = "/todos")
    public String getTodos(Model model,
                           @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Todo> todos = todoService.findAll(pageNumber, ROW_PER_PAGE);

        long count = todoService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("todos", todos);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "todos-list";
    }

    @GetMapping(value = "/todos/{todoId}")
    public String getTodoById(Model model, @PathVariable Integer todoId) {
        Todo todo = null;
        String errorMessage = null;
        try {
            todo = todoService.findById(todoId);
        } catch (Exception ex) {
            errorMessage = "Todo not found";
        }

        model.addAttribute("todo", todo);
        model.addAttribute("allowDelete", false);
        model.addAttribute("errorMessage", errorMessage);
        return "todo-view";
    }

    @GetMapping(value = "/todos/add")
    //@RequestMapping(value = "/todos/add", method = RequestMethod.GET)
    public String showAddTodo(Model model) {
        Todo todo = new Todo();
        model.addAttribute("add", true);
        model.addAttribute("todo", todo);
        model.addAttribute("actionUrl", "/todos/add");

        return "todo-edit";
    }

    @PostMapping(value = "/todos/add")
    //@RequestMapping(value = "/todos/add", method = RequestMethod.POST)
    public String addTodo(Model model,
                          @ModelAttribute("todo") Todo todo) {
        try {
            Todo newTodo = todoService.save(todo);
            return "redirect:/todos/" + String.valueOf(newTodo.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();

            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("todo", todo);
            model.addAttribute("add", true);
            return "todo-edit";
        }
    }

    @GetMapping(value = ["/todos/{todoId}/edit"])
    public String showEditTodo(Model model, @PathVariable Integer todoId) {
        Todo todo = null;
        String errorMessage = null;
        try {
            todo = todoService.findById(todoId);
        } catch (Exception ex) {
            errorMessage = "Todo not found";
        }
        model.addAttribute("add", false);
        model.addAttribute("todo", todo);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("actionUrl",
                "/todos/" + (todo == null ? 0 : todo.getId()) + "/edit");
        return "todo-edit";
    }

    @PostMapping(value = ["/todos/{todoId}/edit"])
    public String updateTodo(Model model,
                             @PathVariable Integer todoId,
                             @ModelAttribute("todo") Todo todo) {
        try {
            println(todo.getCompleted())
            todo.setId(todoId);
            todoService.update(todo);
            return "redirect:/todos/" + String.valueOf(todo.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage()
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "todo-edit";
        }
    }

    @GetMapping(value = ["/todos/{todoId}/delete"])
    public String showDeleteTodoById(
            Model model, @PathVariable Integer todoId) {
        Todo todo = null;
        String errorMessage = null;
        try {
            todo = todoService.findById(todoId);
        } catch (Exception ex) {
            errorMessage = "Todo not found";

        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("todo", todo);
        model.addAttribute("errorMessage", errorMessage);
        return "todo-view";
    }

    @PostMapping(value = ["/todos/{todoId}/delete"])
    public String deleteTodoById(
            Model model, @PathVariable Integer todoId) {
        try {
            todoService.deleteById(todoId);
            return "redirect:/todos";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            model.addAttribute("errorMessage", errorMessage);
            return "todo-view";
        }
    }
}
