package de.allianz.project.controller;

import de.allianz.project.config.ModelMapperConfig;
import de.allianz.project.dto.ToDoCreateDTO;
import de.allianz.project.dto.ToDoUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import de.allianz.project.service.ToDoService;
import de.allianz.project.entity.ToDo;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ToDos")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;
    private final ModelMapper modelMapper;

    //@Autowired
    //public ToDoController(ToDoService toDoService) { this.toDoService = toDoService;}

    /*
    @PostMapping // POST (erstellen)
    public ToDo createToDo(@RequestBody ToDo toDo) {
        return this.toDoService.createToDo(toDo);
    }


    @PostMapping // POST (erstellen)
    public ToDo createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO) {
        //ToDo toDo = new ToDo(); // erstellen neues ToDo, braucht nicht wenn ModleMapping
        //toDo.setTitle(toDoCreateDTO.getTitle()); // neue title in to_Do abgespeichert, braucht nicht wenn ModleMapping
        //toDo.setDescription(toDoCreateDTO.getDescription()); // neue description in to_Do abgespeichert, braucht nicht wenn ModleMapping
        return this.toDoService.createToDo(modelMapper.map(toDoCreateDTO, ToDo.class)); // mappen von Source to Destination; mit ""class" weiss dass neues Objekt erstellen muss
                                                                                        // map das Objekt auf die Klasse, und bekommen neues ToDo zurückgeliefert
    }
     */

    @PostMapping // POST (erstellen)
    @PreAuthorize("hasRole('TODO_CREATE')")
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO) {
        //ToDo toDo = new ToDo(); // erstellen neues ToDo, braucht nicht wenn ModleMapping
        //toDo.setTitle(toDoCreateDTO.getTitle()); // neue title in to_Do abgespeichert, braucht nicht wenn ModleMapping
        //toDo.setDescription(toDoCreateDTO.getDescription()); // neue description in to_Do abgespeichert, braucht nicht wenn ModleMapping
        return new ResponseEntity<>(this.toDoService.createToDo(modelMapper.map(toDoCreateDTO, ToDo.class)), HttpStatus.CREATED); // mappen von Source to Destination; mit ""class" weiss dass neues Objekt erstellen muss
        // map das Objekt auf die Klasse, und bekommen neues ToDo zurückgeliefert
    }

    /*
    @PutMapping // PUT (update)
    public ToDo updateToDo(@RequestBody ToDo toDo) {
        return this.toDoService.updateToDo(toDo);
    }
     */

    @PutMapping // PUT (update)
    @PreAuthorize("hasRole('TODO_UPDATE')")
    public ToDo updateToDo(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO) {
        ToDo toDo = this.toDoService.getToDoById(toDoUpdateDTO.getId());
        modelMapper.map(toDoUpdateDTO, toDo);


        //ToDo toDo = this.toDoService.getToDoById(toDoUpdateDTO.getId());
        //toDo.setTitle(toDoUpdateDTO.getTitle());
        //toDo.setDescription(toDoUpdateDTO.getDescription());

        // detalhiert; statt drei Zeilen oben
        //ToDo toDo = new ToDo(); // erstellen neues To_Do
        //toDo.setId(toDoUpdateDTO.getId());
        //toDo.setStatus(toDoUpdateDTO.getStatus());
        //toDo.setTitle(toDoUpdateDTO.getTitle());
        //toDo.setDescription(toDoUpdateDTO.getDescription());
        //toDo.setPriority(toDoUpdateDTO.getPriority());
        //toDo.setDueDate(toDoUpdateDTO.getDueDate());
        return this.toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/{id}") // DELETE (löschen)
    @PreAuthorize("hasRole('TODO_DELETE')")
    public void deleteToDo(@PathVariable("id") Long id) {
        this.toDoService.deleteToDo(id);
    }

    @GetMapping("/{id}") // GET (lesen)
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public ToDo getToDoById(@PathVariable("id") Long id) {
        return this.toDoService.getToDoById(id);
    }

    @GetMapping // GET (lesen)
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public List<ToDo> getToDos() {
        return this.toDoService.getToDos();
    }

    @GetMapping("/listCompleted") // GET (lesen)
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public List<ToDo> getCompletedToDos() {
        return this.toDoService.getCompletedToDos();
    }

    @GetMapping("/listNotCompleted") // GET (lesen)
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public List<ToDo> getNotCompletedToDos() {
        return this.toDoService.getNotCompletedToDos();
    }

    @GetMapping("/countCompleted") // GET (lesen)
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public Long countCompletedToDos() {
        return this.toDoService.countCompletedToDos();
    }

    @GetMapping("/countNotCompleted") // GET (lesen)
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public Long countNotCompletedToDos() {
        return this.toDoService.countNotCompletedToDos();
    }

}