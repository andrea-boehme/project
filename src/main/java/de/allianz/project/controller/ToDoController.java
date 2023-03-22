package de.allianz.project.controller;

import de.allianz.project.dto.ToDoCreateDTO;
import de.allianz.project.dto.ToDoUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import de.allianz.project.service.ToDoService;
import de.allianz.project.entity.ToDo;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ToDos")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    /*
    @PostMapping // POST (erstellen)
    public ToDo createToDo(@RequestBody ToDo toDo) {
        return this.toDoService.createToDo(toDo);
    }
     */

    @PostMapping // POST (erstellen)
    public ToDo createToDo(@Valid ToDoCreateDTO toDoCreateDTO) {
        ToDo toDo = new ToDo(); // erstellen neues ToDo
        toDo.setTitle(toDoCreateDTO.getTitle()); // neue title in to_Do abgespeichert
        toDo.setDescription(toDoCreateDTO.getDescription()); // neue description in to_Do abgespeichert
        return this.toDoService.createToDo(toDo);
    }

    /*
    @PutMapping // PUT (update)
    public ToDo updateToDo(@RequestBody ToDo toDo) {
        return this.toDoService.updateToDo(toDo);
    }
     */

    @PutMapping // PUT (update)
    public ToDo updateToDo(@Valid ToDoUpdateDTO toDoUpdateeDTO) {
        ToDo toDo = new ToDo(); // erstellen neues ToDo
        toDo.setId(toDoUpdateeDTO.getId());
        toDo.setStatus(toDoUpdateeDTO.getStatus());
        toDo.setTitle(toDoUpdateeDTO.getTitle());
        toDo.setDescription(toDoUpdateeDTO.getDescription());
        return this.toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/{id}") // DELETE (löschen)
    public void deleteToDo(@PathVariable("id") Long id) {
        this.toDoService.deleteToDo(id);
    }

    @GetMapping("/{id}") // GET (lesen)
    public ToDo getToDoById(@PathVariable("id") Long id) {
        return this.toDoService.getToDoById(id);
    }

    @GetMapping // GET (lesen)
    public List<ToDo> getToDos() {
        return this.toDoService.getToDos();
    }

    @GetMapping("/listCompleted") // GET (lesen)
    public List<ToDo> getCompletedToDos() {
        return this.toDoService.getCompletedToDos();
    }

    @GetMapping("/listNotCompleted") // GET (lesen)
    public List<ToDo> getNotCompletedToDos() {
        return this.toDoService.getNotCompletedToDos();
    }

    @GetMapping("/countCompleted") // GET (lesen)
    public Long countCompletedToDos() {
        return this.toDoService.countCompletedToDos();
    }

    @GetMapping("/countNotCompleted") // GET (lesen)
    public Long countNotCompletedToDos() {
        return this.toDoService.countNotCompletedToDos();
    }

    //getById ergänzen
}