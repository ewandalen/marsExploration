package com.contaazul.marsExploration;

import com.contaazul.marsExploration.exception.InvalidCommandException;
import com.contaazul.marsExploration.model.Robot;
import com.contaazul.marsExploration.service.MarsExplorationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class MarsExplorationServiceTest {

	@InjectMocks
	private MarsExplorationService marsExplorationService;

	@BeforeEach
	public void setup() {
	}

	@Test
	public void testBasicMovement() {
		String commands = "M";
		Robot robot = marsExplorationService.executeCommands(commands);
		Assertions.assertEquals("(0, 1, N)", robot.toString());
	}

	@Test
	public void testTurnLeft() {
		String commands = "L";
		Robot robot = marsExplorationService.executeCommands(commands);
		Assertions.assertEquals("(0, 0, W)", robot.toString());
	}

	@Test
	public void testTurnLeftMoreTimes() {
		String commands = "LLLLL";
		Robot robot = marsExplorationService.executeCommands(commands);
		Assertions.assertEquals("(0, 0, W)", robot.toString());
	}

	@Test
	public void testTurnRigth() {
		String commands = "R";
		Robot robot = marsExplorationService.executeCommands(commands);
		Assertions.assertEquals("(0, 0, E)", robot.toString());
	}

	@Test
	public void testTurnRigthMoreTimes() {
		String commands = "RRRRR";
		Robot robot = marsExplorationService.executeCommands(commands);
		Assertions.assertEquals("(0, 0, E)", robot.toString());
	}

	@Test
	public void testBigCommand() {
		String commands = "MMRMMRMMLMLMLM";
		Robot robot = marsExplorationService.executeCommands(commands);
		Assertions.assertEquals("(2, 1, W)", robot.toString());
	}

	@Test
	public void testMoveOutOfRegion() {
		String commands = "MMMMMM";
		InvalidCommandException exception = Assertions.assertThrows(InvalidCommandException.class, () -> {
			marsExplorationService.executeCommands(commands);
		});
		Assertions.assertEquals("Invalid command.", exception.getMessage());
	}

	@Test
	public void testMoveOutOfRegionSouth() {
		String commands = "RRM";
		InvalidCommandException exception = Assertions.assertThrows(InvalidCommandException.class, () -> {
			marsExplorationService.executeCommands(commands);
		});
		Assertions.assertEquals("Invalid command.", exception.getMessage());
	}

	@Test
	public void testMoveOutOfRegionEast() {
		String commands = "RMMMMMM";
		InvalidCommandException exception = Assertions.assertThrows(InvalidCommandException.class, () -> {
			marsExplorationService.executeCommands(commands);
		});
		Assertions.assertEquals("Invalid command.", exception.getMessage());
	}

	@Test
	public void testMoveOutOfRegionWest() {
		String commands = "LMM";
		InvalidCommandException exception = Assertions.assertThrows(InvalidCommandException.class, () -> {
			marsExplorationService.executeCommands(commands);
		});
		Assertions.assertEquals("Invalid command.", exception.getMessage());
	}

	@Test
	public void testOnlyInvalidCommands() {
		String commands = "AAAAAA";
		InvalidCommandException exception = Assertions.assertThrows(InvalidCommandException.class, () -> {
			marsExplorationService.executeCommands(commands);
		});
		Assertions.assertEquals("Invalid command.", exception.getMessage());
	}

	@Test
	public void testMixedInvalidCommands() {
		String commands = "MMRAMMRAAAAAA";
		InvalidCommandException exception = Assertions.assertThrows(InvalidCommandException.class, () -> {
			marsExplorationService.executeCommands(commands);
		});
		Assertions.assertEquals("Invalid command.", exception.getMessage());
	}

}
