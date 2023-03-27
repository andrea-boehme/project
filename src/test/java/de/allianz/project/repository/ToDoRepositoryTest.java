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
//@RequiredArgsConstructor
public class ToDoRepositoryTest {

    @Autowired

    private ToDoRepository toDoRepository;

    private ToDo toDo1;
    private ToDo toDo2;

    @BeforeEach
    public void create() {

        toDo1 = new ToDo();
        toDo1.setStatus(true);
        toDo2 = new ToDo();
        toDo2.setStatus(false);

        toDoRepository.save(toDo1);
        toDoRepository.save(toDo2);
    }

    @Test
    public void findAllByStatus() {
        assertTrue(toDoRepository.findAllByStatus(true).contains(toDo1));
        assertTrue(toDoRepository.findAllByStatus(false).contains(toDo2));

        assertFalse(toDoRepository.findAllByStatus(false).contains(toDo1));
        assertFalse(toDoRepository.findAllByStatus(true).contains(toDo2));
    }

    @Test
    public void countAllByStatus() {
        assertEquals( 1, toDoRepository.countAllByStatus(true));
        assertNotEquals( 2, toDoRepository.countAllByStatus(true));
        assertEquals( 1, toDoRepository.countAllByStatus(false));
        assertNotEquals( 2, toDoRepository.countAllByStatus(false));
    }

}
