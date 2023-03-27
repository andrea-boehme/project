package de.allianz.project.repository;

import de.allianz.project.entity.ToDo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)  //https://github.com/spring-projects/spring-framework/issues/22286
public class ToDoRepositoryTest {

    private final ToDoRepository toDoRepository;

    private ToDo todo1;
    private ToDo todo2;

    private ToDo todo3;

    @BeforeEach
    public void init() {

        todo1 = new ToDo(null, "Putzen", "Staub saugen", "morgen", "sofort", true);
        todo2 = new ToDo(null, "Lernen", "Aufgaben rechnen", "morgen", "sofort", false);
        todo3 = new ToDo(null, "Einkaufen", "Liste aufschreiben", "morgen", "sofort", true);

        this.toDoRepository.saveAll(List.of(todo1, todo2, todo3));

        /*
        toDo1 = new ToDo();
        toDo1.setStatus(true);
        toDo2 = new ToDo();
        toDo2.setStatus(false);

        toDoRepository.save(toDo1);
        toDoRepository.save(toDo2);
        */
    }

    @Test
    public void findAllByStatus() {
       assertTrue(this.toDoRepository.findAllByStatus(true).contains(todo1));
       assertTrue(this.toDoRepository.findAllByStatus(false).contains(todo2));
       assertTrue(this.toDoRepository.findAllByStatus(true).contains(todo3));

       assertFalse(this.toDoRepository.findAllByStatus(false).contains(todo1));
       assertFalse(this.toDoRepository.findAllByStatus(true).contains(todo2));
       assertFalse(this.toDoRepository.findAllByStatus(false).contains(todo3));
    }

    @Test
    public void countAllByStatus() {
        assertEquals( 2, this.toDoRepository.countAllByStatus(true));
        assertNotEquals( 3, this.toDoRepository.countAllByStatus(true));
        assertEquals( 1, this.toDoRepository.countAllByStatus(false));
        assertNotEquals( 2, this.toDoRepository.countAllByStatus(false));
    }

}
