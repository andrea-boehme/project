package de.allianz.project.database;

import de.allianz.project.entity.ToDo;
import de.allianz.project.repository.ToDoRepository;
import de.allianz.project.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor
@Log
public class DatabasePopulator implements CommandLineRunner {

    private final ToDoRepository toDoRepository;
    private final ToDoService toDoService;

    @Value("key")
    public String key;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(key);

        log.info("DATABASE WILL BE INITIALIZED");
        final ToDo toDo1 = new ToDo(null, "Putzen", "Staub saugen", "morgen", "sofort", true);
        final ToDo toDo2 = new ToDo(null, "Lernen", "Aufgaben rechnen", "morgen", "sofort", false);
        final ToDo toDo3 = new ToDo(null, "Einkaufen", "Liste aufschreiben", "morgen", "sofort", true);

        toDoRepository.saveAll(List.of(toDo1, toDo2, toDo3));

        //System.out.println("Anzahl der ToDos: " + toDoRepository.count());

        System.out.println("---------------");
        System.out.println("All ToDos: " + toDoService.getToDos());
        System.out.println("---------------");
        System.out.println("Completed ToDos: " + toDoService.getCompletedToDos());
        System.out.println("---------------");
        System.out.println("Not completed ToDos: " + toDoService.getNotCompletedToDos());
        System.out.println("---------------");
        System.out.println("Number of completed ToDos: " + toDoService.countCompletedToDos());
        System.out.println("---------------");
        System.out.println("Number of not completed ToDos: " + toDoService.countNotCompletedToDos());
    }
}
