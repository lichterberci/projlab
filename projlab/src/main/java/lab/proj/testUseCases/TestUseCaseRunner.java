package lab.proj.testUseCases;

import java.util.List;
import java.util.Map;

public class TestUseCaseRunner {
	private static Map<String, Class<? extends TestUseCase>> useCases = Map.of(
			"StudentDropsABeer", StudentDropsABeer.class,
			"StudentSwitchesRooms", StudentSwitchesRooms.class,
			"TwoTeachersOneStudent", TwoTeachersOneStudent.class,
			"CamembertUsage", CamembertUsage.class,
			"Gas", Gas.class,
			"StudentDefendsWithBeer", StudentDefendsWithBeer.class
	);

	public static void runTest(String id) {
		try {
			TestUseCase useCase = useCases.get(id).newInstance();
			useCase.runUseCase();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<String> getAvailableUseCases() {
		return useCases.keySet().stream().toList();
	}
}
