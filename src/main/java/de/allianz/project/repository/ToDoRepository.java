package de.allianz.project.repository;

import de.allianz.project.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//derived methods (in Repository) sind JAVA Methoden (z.B. findAllBy...) die zur Compilezeit in SQL Statements überführt werden
// d.h. JAVA Methode wird in SQL Statement übersetzt. SQL Statements schneller
@Repository // zeichnet das Interface als Repository aus
public interface ToDoRepository extends CrudRepository<ToDo, Long> { // Entität die gespeichert wird, Typ des Primärschlüssels (ID)
// Interface kann Methoden definieren, die in den Klassen überschrieben werden
    List<ToDo> findAllByStatusIsTrue();
    List<ToDo> findAllByStatusIsFalse();
    List<ToDo> findAllByStatus(Boolean status); // könnte man generisch so machen

    Long countAllByStatusIsTrue();
    Long countAllByStatusIsFalse();
    Long countAllByStatus(Boolean status); // könnte man generisch so machen

    List<ToDo> findAllByTitleContains(String title);
}