package de.allianz.project.database;

import de.allianz.project.entity.ToDo;
import de.allianz.project.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    private final ToDoRepository toDoRepository;
    @Override
    public void run(String... args) throws Exception {

        final ToDo todo1 = new ToDo(null, "Putzen", "Staub saugen", "morgen", "sofort", true);
        final ToDo todo2 = new ToDo(null, "Lernen", "Aufgaben rechnen", "morgen", "sofort", false);
        final ToDo todo3 = new ToDo(null, "Einkaufen", "Liste aufschreiben", "morgen", "sofort", true);

        toDoRepository.saveAll(List.of(todo1, todo2, todo3));

        System.out.println("Anzahl der ToDos: " + toDoRepository.count());
    }
}
