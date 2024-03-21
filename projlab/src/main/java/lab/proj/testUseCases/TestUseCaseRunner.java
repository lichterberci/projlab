package lab.proj.testUseCases;

import java.util.Map;

public class TestUseCaseRunner {
	static Map<String, Class<? extends TestUseCase>> useCases = Map.of(
		"StudentDropsABeer", StudentDropsABeer.class
	);

	public static void runTest(String id) {
		try {
			TestUseCase useCase = useCases.get(id).newInstance();
			useCase.runUseCase();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
