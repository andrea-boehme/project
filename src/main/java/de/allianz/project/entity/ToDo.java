package de.allianz.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor // automatisch leeren constructor erstellt
@AllArgsConstructor // automatisch constructor mit allen arguments erstellt
@Getter
@Setter
@Entity
@ToString
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;
    private String dueDate;
    private String priority;

    private Boolean status;
}


/* // braucht man nicht leeren constructor da oben @NoArgsConstructor
    public ToDo() {

    }

    /* // braucht man nicht da oben @AllArgsConstructor
    public ToDo(Long id, String title, String description, String dueDate, String priority, Boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

     */