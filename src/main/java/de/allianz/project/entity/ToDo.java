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
@Entity // zeichnet Klasse als Entität aus (Klasse die in DB gespeichert wird
@ToString
public class ToDo {
    @Id // legt den Primärschlüssel (ID) fest
    @GeneratedValue(strategy = GenerationType.IDENTITY) // wenn Objekt erstellt, automatisch ID generiert, wenn in DB geschrieben wird
    private Long id;

    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private Boolean status;
}

/* // braucht man nicht leeren constructor da oben @NoArgsConstructor (lombok)
    public ToDo() {
    }

/* // braucht man nicht da oben @AllArgsConstructor (lombok)
    public ToDo(Long id, String title, String description, String dueDate, String priority, Boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }
*/