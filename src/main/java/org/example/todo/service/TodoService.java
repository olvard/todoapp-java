package org.example.todo.service;

import org.example.todo.model.TodoItem;
import org.springframework.stereotype.Service;
import org.example.todo.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService{

    private final TodoRepository repo;

    public TodoService(TodoRepository repo){
        this.repo = repo;
    }

    public List<TodoItem> getAllTodos(){
        return repo.findAll();
    }

    public TodoItem addTodo(TodoItem item){
        return repo.save(item);
    }

    public void deleteTodo(Long id){
        repo.deleteById(id);
    }

    public TodoItem updateTodo(Long id, TodoItem updatedItem){

        return repo.findById(id).map(todo -> {
            todo.setCompleted(updatedItem.getCompleted());
            todo.setTitle(updatedItem.getTitle());
            return repo.save(todo);
        }).orElseThrow(()-> new RuntimeException("Item not found"));

    }

}




//public class TodoService {
//
//    private final List<TodoItem> todos = new ArrayList<>();
//    private final AtomicLong idCounter = new AtomicLong();
//
//    public TodoItem addTodo(TodoItem item){
//        item.setId(idCounter.incrementAndGet());
//        item.setCompleted(false);
//
//        todos.add(item);
//        return item;
//    }
//
//    public void deleteTodo(Long id){
//        todos.removeIf(item -> item.getId().equals(id));
//    }
//
//    public List<TodoItem> getAllTodos() {
//        return todos;
//    }
//
//    public TodoItem updateTodo(Long id, TodoItem updatedItem){
//        for(TodoItem item : todos){
//            if(item.getId().equals(id)){
//                item.setCompleted(updatedItem.getCompleted());
//            }
//        }
//
//        return updatedItem;
//    }
//
//}
