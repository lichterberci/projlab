package lab.proj.utils;

import java.io.PrintWriter;

/**
 * A test case class for unit testing.
 */
public class TestCase {
    /**
     * The ID of the test case.
     */
    private final int id;
    /**
     * The result of the test case.
     */
    private TestResult result = TestResult.INCONCLUSIVE;

    /**
     * Constructs a new test case with a specified ID.
     *
     * @param id The ID of the test case.
     */
    public TestCase(int id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the test case.
     *
     * @return The ID of the test case.
     */
    public void Success() {
        result = TestResult.SUCCESS;
    }

    /**
        * Marks the test case as failed.
     */
    public void Fail() {
        result = TestResult.FAIL;
    }

    /**
     * Marks the test case as errored.
     */
    public void Error() {
        result = TestResult.ERROR;
    }

    /**
     * Marks the test case as inconclusive.
     */
    public void Report(PrintWriter pw) {
        pw.printf("Test %d - %s%n", id, result.name());
    }

    /**
     * An enumeration representing the result of a test case.
     */
    public enum TestResult {
        SUCCESS, FAIL, ERROR, INCONCLUSIVE
    }
}
