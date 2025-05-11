package org.example.todo.controller;

import org.example.todo.model.TodoItem;
import org.example.todo.repository.TodoRepository;
import org.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoItem> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public TodoItem addTodo(@RequestBody TodoItem item){
        return todoService.addTodo(item);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodo(@PathVariable Long id, @RequestBody TodoItem item){

        return todoService.updateTodo(id, item);
    }
}
