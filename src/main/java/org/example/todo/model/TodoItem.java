package org.example.todo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class TodoItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;

    public TodoItem(){}

    public TodoItem(Long id, String title, boolean completed){
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getTitle(){ return title; }
    public void setTitle(String name){ this.title = name; }

    public boolean getCompleted(){ return completed; }
    public void setCompleted(boolean completed){ this.completed = completed; }


}
