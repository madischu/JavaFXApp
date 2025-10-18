import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerProfileTest {
    @Test
    void testAddScoreUpdatesHighScore() {
        PlayerProfile profile = new PlayerProfile("Alice");
        profile.addScore(new ScoreRecord(5, 1));
        profile.addScore(new ScoreRecord(10, 2));
        assertEquals(10, profile.getHighScore());
    }

    @Test
    void testGetScoreHistory() {
        PlayerProfile profile = new PlayerProfile("Bob");
        profile.addScore(new ScoreRecord(7, 1));
        assertEquals(1, profile.getScoreHistory().size());
    }
}
