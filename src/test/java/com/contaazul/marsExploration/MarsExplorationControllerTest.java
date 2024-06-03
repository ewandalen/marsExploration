package com.contaazul.marsExploration;

import com.contaazul.marsExploration.controller.MarsExplorationController;
import com.contaazul.marsExploration.exception.InvalidCommandException;
import com.contaazul.marsExploration.model.Robot;
import com.contaazul.marsExploration.model.enums.DirectionEnum;
import com.contaazul.marsExploration.service.MarsExplorationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MarsExplorationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MarsExplorationService marsExplorationService;

    @InjectMocks
    private MarsExplorationController marsExplorationController;

    public MarsExplorationControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(marsExplorationController).build();
    }

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testBasicMovement() throws Exception {

        when(marsExplorationService.executeCommands(anyString())).thenAnswer(invocation -> {
            Robot robot = new Robot();
            robot.setX(0);
            robot.setY(1);
            robot.setDirection(DirectionEnum.NORTH);
            return robot;
        });

        mockMvc.perform(post("/mars/M")
                        .contentType(MediaType.TEXT_PLAIN))
                        .andExpect(status().isOk())
                        .andExpect(content().string("(0, 1, N)"));
    }

    @Test
    public void testTurnLeft() throws Exception {
        when(marsExplorationService.executeCommands(anyString())).thenAnswer(invocation -> {
            Robot robot = new Robot();
            robot.setX(0);
            robot.setY(1);
            robot.setDirection(DirectionEnum.WEST);
            return robot;
        });

        mockMvc.perform(post("/mars/L")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("(0, 1, W)"));
    }

    @Test
    public void testTurnRigth() throws Exception {
        when(marsExplorationService.executeCommands(anyString())).thenAnswer(invocation -> {
            Robot robot = new Robot();
            robot.setX(0);
            robot.setY(0);
            robot.setDirection(DirectionEnum.EAST);
            return robot;
        });

        mockMvc.perform(post("/mars/R")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("(0, 0, E)"));
    }

    @Test
    public void testBigCommand() throws Exception {
        when(marsExplorationService.executeCommands(anyString())).thenAnswer(invocation -> {
            Robot robot = new Robot();
            robot.setX(2);
            robot.setY(0);
            robot.setDirection(DirectionEnum.SOUTH);
            return robot;
        });

        mockMvc.perform(post("/mars/MMRMMRMM")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("(2, 0, S)"));
    }

    @Test
    public void testMoveOutOfRegion() throws Exception {
        when(marsExplorationService.executeCommands(anyString())).thenThrow(new InvalidCommandException());

        mockMvc.perform(post("/mars/MMMMMM")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testMoveOutOfRegionSouth() throws Exception {
        when(marsExplorationService.executeCommands(anyString())).thenThrow(new InvalidCommandException());

        mockMvc.perform(post("/mars/RRM")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testMoveOutOfRegionEast() throws Exception {
        String commands = "RMMMMMM";
        when(marsExplorationService.executeCommands(anyString())).thenThrow(new InvalidCommandException());

        mockMvc.perform(post("/mars/RMMMMMM")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testMoveOutOfRegionWest() throws Exception {
        when(marsExplorationService.executeCommands(anyString())).thenThrow(new InvalidCommandException());

        mockMvc.perform(post("/mars/LMM")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testOnlyInvalidCommands() throws Exception {
        when(marsExplorationService.executeCommands(anyString())).thenThrow(new InvalidCommandException());

        mockMvc.perform(post("/mars/AAAAAA")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testMixedInvalidCommands() throws Exception {
        String commands = "MMRAMMRAAAAAA";
        when(marsExplorationService.executeCommands(anyString())).thenThrow(new InvalidCommandException());

        mockMvc.perform(post("/mars/MMRAMMRAAAAAA")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }

}
