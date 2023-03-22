package de.allianz.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ToDoCreateDTO {

    @NotBlank
    private final String title;

    @NotBlank
    private final String description;
}
