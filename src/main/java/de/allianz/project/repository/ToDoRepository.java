package de.allianz.project.repository;

import de.allianz.project.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    List<ToDo> findAllByStatusIsTrue();
    List<ToDo> findAllByStatusIsFalse();
    List<ToDo> findAllByStatus(Boolean status); // könnte man generisch so machen

    Long countAllByStatusIsTrue();
    Long countAllByStatusIsFalse();
    Long countAllByStatus(Boolean status); // könnte man generisch so machen
}