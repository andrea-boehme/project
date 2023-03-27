package de.allianz.project.controller;

import de.allianz.project.entity.ToDo;
import de.allianz.project.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ToDoService toDoService;
    @MockBean
    private ModelMapper modelMapper;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;
    private List<ToDo> toDoArrayList = new ArrayList<>();

    // ID eingeben da hier nicht mit DB arbeiten.
    @BeforeEach
    public void init(){
        toDo1 = new ToDo(1L, "Putzen", "Staub saugen", "morgen", "sofort", true);
        toDo2 = new ToDo(2L, "Lernen", "Aufgaben rechnen", "morgen", "sofort", false);
        toDo3 = new ToDo(3L, "Einkaufen", "Liste aufschreiben", "morgen", "sofort", true);
        this.toDoArrayList.addAll(Arrays.asList(toDo1,toDo2,toDo3));
    }


    @Test
    public void ShouldGetToDos() throws Exception
    {
        when(this.toDoService.getToDos()).thenReturn(this.toDoArrayList);

        this.mockMvc.perform(
                get("/toDos"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {
                                             "id": 1,
                                             "title": "Putzen",
                                             "description": "Staub saugen",
                                             "dueDate": "morgen",
                                             "priority": "sofort",
                                             "status": true
                                         },
                                         {
                                             "id": 2,
                                             "title": "Lernen",
                                             "description": "Aufgaben rechnen",
                                             "dueDate": "morgen",
                                             "priority": "sofort",
                                             "status": false
                                         },
                                         {
                                             "id": 3,
                                             "title": "Einkaufen",
                                             "description": "Liste aufschreiben",
                                             "dueDate": "morgen",
                                             "priority": "sofort",
                                             "status": true
                                         }
                                ]
                                """
                )); // Daten von Postman

    }

    @Test
    public void createToDo(){

    }

    @Test
    public void updateToDo(){

    }

    @Test
    public void deleteToDo(){

    }

    @Test
    public void getToDoById(){

    }


}