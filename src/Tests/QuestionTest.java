import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    @Test
    void testCheckAnswerCorrect() {
        String[] options = {"A", "B", "C", "D"};
        MultipleChoiceQuestion q = new MultipleChoiceQuestion(
                "What is 2+2?", options, "C", "Math", 1, "2+2 = 4");
        assertTrue(q.checkAnswer("C"));
    }

    @Test
    void testCheckAnswerIncorrect() {
        String[] options = {"A", "B", "C", "D"};
        MultipleChoiceQuestion q = new MultipleChoiceQuestion(
                "What is 2+2?", options, "C", "Math", 1, "2+2 = 4");
        assertFalse(q.checkAnswer("A"));
    }
}
