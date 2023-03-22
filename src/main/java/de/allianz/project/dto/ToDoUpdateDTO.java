package de.allianz.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

@Getter
@ToString
@RequiredArgsConstructor
public class ToDoUpdateDTO {
    @NotNull
    @Positive
    private final Long id;  // sichergestellt dass beim erstellen (create) keine id vergeben wird,
                            // aber beim update muss eine von der Datenbank gegeben vorhanden sein

    @NotNull // bei boolean, notnull verwenden
    private final Boolean status= false;

    @NotBlank
    private final String title;
    @NotBlank
    private final String description;
}
