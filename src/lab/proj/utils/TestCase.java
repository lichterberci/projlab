package lab.proj.utils;

import java.io.PrintWriter;

public class TestCase {
    private final int id;
    private TestResult result = TestResult.INCONCLUSIVE;

    public TestCase(int id) {
        this.id = id;
    }

    public void Success() {
        result = TestResult.SUCCESS;
    }

    public void Fail() {
        result = TestResult.FAIL;
    }

    public void Error() {
        result = TestResult.ERROR;
    }

    public void Report(PrintWriter pw) {
        pw.printf("Test %d - %s%n", id, result.name());
    }

    public enum TestResult {
        SUCCESS, FAIL, ERROR, INCONCLUSIVE
    }
}
