package de.allianz.project.service;

import de.allianz.project.entity.ToDo;
import de.allianz.project.exception.GlobalExceptionHandler;
import de.allianz.project.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    // To_Do erstellen; keine id vergeben da es die Datenbank macht
    /**
     * creates new "To_Do" in database
     * @param toDo
     * @return saved To_Do in database
     */
    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    } // führt nicht direkt Transaktionen in der DB aus, sondern verwendet Repositoryschicht

    // To_Do updaten
    /**
     * updates selected "To_Do" in database
     * @param toDo
     * @return saved updated To_Do in database
     */
    public ToDo updateToDo(ToDo toDo) {
        ToDo selectedToDo = toDoRepository.findById(toDo.getId()).orElseThrow(
                 () -> new EntityNotFoundException("ToDo not found!"));

        selectedToDo.setTitle(toDo.getTitle());
        selectedToDo.setDescription(toDo.getDescription());
        selectedToDo.setDueDate(toDo.getDueDate());
        selectedToDo.setPriority(toDo.getPriority());
        selectedToDo.setStatus(toDo.getStatus());

        return this.toDoRepository.save(selectedToDo);
        // könnte hier auch nicht neue parameter setzen und mit ".save(toDo)" überschreiben
    }

    // To_Do löschen
    /**
     * deletes selected "To_Do" from database
     * @param id
     */
    public void deleteToDo(Long id) {
        this.toDoRepository.deleteById(id);
    }

    // bestimmtes To_Do lesen
    /**
     * get specific To_Do by id from database
     * @param id
     */
    public ToDo getToDoById(Long id) {
        return this.toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ToDo not found!"));
    }

    // alle ToDos lesen
    /**
     * gets all existing To_Dos saved in database
     * @return List with all To_Dos
     */
    // SQL Statements viel schneller und sind in Repository verfügbar (derived methods)
    public List<ToDo> getToDos() {
        return (List<ToDo>) this.toDoRepository.findAll();
    }

    // erledigte ToDos lesen; in Interface "ToDoRepository" wird Methode definiert.
    /**
     * gets To_Dos with status "complete" (status: true) saved in database
     * @return List with To_Dos that have status: true
     */
    //public List<ToDo> getCompletedToDos() {
        //return this.toDoRepository.findAllByStatusIsTrue();
    //}

    // offene ToDos lesen; in Interface "ToDoRepository" wird Methode definiert.
    /**
     * gets To_Dos with status "not complete" (status: false) saved in database
     * @return List with To_Dos that have status: false
     */
    //public List<ToDo> getNotCompletedToDos() {
        //return this.toDoRepository.findAllByStatusIsFalse();
    //}

    // Anzahl der erledigten ToDos lesen; in Interface "ToDoRepository" wird Methode definiert.
    /**
     * counts number of To-Dos with status "complete" (status: true)
     * @return Number of To_Dos that have status: true
     */
    //public Long countCompletedToDos() {
        //return this.toDoRepository.countAllByStatusIsTrue();
    //}

    // Anzahl der offenen ToDos lesen; in Interface "ToDoRepository" wird Methode definiert.
    /**
     * counts number of To-Dos with status "complete" (status: false)
     * @return Number of To_Dos that have status: false
     */
    //public Long countNotCompletedToDos() {
        //return this.toDoRepository.countAllByStatusIsFalse();
    //}


    public List<ToDo> getCompletedToDos() {
        return this.toDoRepository.findAllByStatus(true);
    }

    public List<ToDo> getNotCompletedToDos() {
        return this.toDoRepository.findAllByStatus(false);
    }

    public Long countCompletedToDos() {
        return this.toDoRepository.countAllByStatus(true);
    }

    public Long countNotCompletedToDos() {
        return this.toDoRepository.countAllByStatus(false);
    }

    //public List<ToDo> findAllByTitleContains() {
        //return this.toDoRepository.findAllByStatusIsFalse();
    //}

}
