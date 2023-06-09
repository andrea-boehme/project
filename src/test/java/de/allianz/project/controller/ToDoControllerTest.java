package de.allianz.project.controller;

import de.allianz.project.config.PasswordEncoderConfig;
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
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@Import(PasswordEncoderConfig.class)
@WebMvcTest(ToDoController.class)
@WithMockUser
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

    private ToDo toDo4;
    private List<ToDo> toDoArrayList = new ArrayList<>();

    // ID eingeben da hier nicht mit DB arbeiten.
    @BeforeEach
    public void init() {
        toDo1 = new ToDo(1L, "Putzen", "Staub saugen", "morgen", "sofort", true);
        toDo2 = new ToDo(2L, "Lernen", "Aufgaben rechnen", "morgen", "sofort", false);
        toDo3 = new ToDo(3L, "Einkaufen", "Liste aufschreiben", "morgen", "sofort", true);
        this.toDoArrayList.addAll(Arrays.asList(toDo1, toDo2, toDo3));
    }


    @Test
    @WithMockUser
    public void ShouldGetToDos() throws Exception {
        when(this.toDoService.getToDos()).thenReturn(this.toDoArrayList);

        this.mockMvc.perform(
                        get("/ToDos"))
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
    @WithMockUser
    public void ShouldCreateToDo() throws Exception {

        toDo4 = new ToDo(4L, "Sport", "Joggen gehen", "morgen", "sofort", true);

        when(this.toDoService.createToDo(any())).thenReturn(toDo4);
        //when(this.modelMapper.map(any(), any())).thenReturn(toDo4);

        this.mockMvc.perform(
                        post("/ToDos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        """
                                                                 
                                                    {
                                                             "id": null,
                                                             "title": "Sport",
                                                             "description": "Joggen gehen",
                                                             "dueDate": "morgen",
                                                             "priority": "sofort",
                                                             "status": true
                                                         }
                                                                                
                                                """
                                ))
                .andExpect(status().isCreated());
        //.andExpect(content().json());
    }

    @Test
    @WithMockUser
    public void ShouldUpdateToDo() throws Exception { // z.B. status von toDo1 auf false setzen
        ToDo toDoOld = new ToDo(1L, "Putzen", "Staub saugen", "morgen", "sofort", true);
        ToDo toDoNew = new ToDo(1L, "Putzen", "Staub saugen", "morgen", "sofort", false);

        when(this.toDoService.getToDoById(any(Long.class))).thenReturn(toDoOld);
        when(this.toDoService.updateToDo(any(ToDo.class))).thenReturn(toDoNew);
        when(this.modelMapper.map(any(), any())).thenReturn(toDoNew);

        this.mockMvc.perform(
                        put("/ToDos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        """          
                                                    {
                                                             "id": 1,
                                                             "title": "Putzen",
                                                             "description": "Staub saugen",
                                                             "dueDate": "morgen",
                                                             "priority": "sofort",
                                                             "status": false
                                                         }                        
                                                """
                                ))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """          
                                    {
                                             "id": 1,
                                             "title": "Putzen",
                                             "description": "Staub saugen",
                                             "dueDate": "morgen",
                                             "priority": "sofort",
                                             "status": false
                                         }                    
                                """
                ));
    }

    @Test
    @WithMockUser
    public void ShouldDeleteToDo() throws Exception {

        this.mockMvc.perform(delete("/ToDos/1"))
                .andExpect((status().isNoContent()));
    }

    @Test
    @WithMockUser
    public void ShouldGetToDoById() throws Exception {

        when(this.toDoService.getToDoById(any(Long.class))).thenReturn(toDo1);

        this.mockMvc.perform(get("/ToDos/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                                     "id": 1,
                                                     "title": "Putzen",
                                                     "description": "Staub saugen",
                                                     "dueDate": "morgen",
                                                     "priority": "sofort",
                                                     "status": true
                                             }
                                """
                ));
    }

}
