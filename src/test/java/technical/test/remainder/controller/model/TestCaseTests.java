package technical.test.remainder.controller.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import technical.test.remainder.model.TestCase;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestCaseTests {

    @Test
    public void testEquals() {
        TestCase testCase1 = new TestCase(10, 3, 15);
        TestCase testCase2 = new TestCase(10, 3, 15);
        assertEquals(testCase1, testCase2);
    }

    @Test
    public void testHashCode() {
        TestCase testCase = new TestCase(10, 3, 15);
        int hashCode = testCase.hashCode();
        assertNotNull(hashCode);
    }
}
