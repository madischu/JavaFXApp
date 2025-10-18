import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class GameManagerTest {
    @Test
    void testGameManagerScoreUpdate() {
        PlayerProfile profile = new PlayerProfile("Charlie");
        GameManager manager = new GameManager(profile, 3);

        List<Question> bank = new ArrayList<>();
        bank.add(new MultipleChoiceQuestion("Q1", new String[]{"A", "B", "C", "D"}, "A", "Gen", 1, "Ans A"));
        bank.add(new MultipleChoiceQuestion("Q2", new String[]{"A", "B", "C", "D"}, "B", "Gen", 1, "Ans B"));
        bank.add(new MultipleChoiceQuestion("Q3", new String[]{"A", "B", "C", "D"}, "C", "Gen", 1, "Ans C"));

        manager.loadQuestions(bank);
        manager.startGame();

        Question q1 = manager.nextQuestion();
        if (q1 != null) {
            manager.updateScore(q1.checkAnswer("A"));
        }

        Question q2 = manager.nextQuestion();
        if (q2 != null) {
            manager.updateScore(q2.checkAnswer("Wrong"));
        }

        assertTrue(manager.getCurrentScore() >= 0);
        assertEquals(1, profile.getScoreHistory().size());
    }
}
