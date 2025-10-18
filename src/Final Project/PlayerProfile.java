import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's profile, including their name,
 * score history, and high score.
 */
public class PlayerProfile 
{
    private String playerName;
    private List<ScoreRecord> scoreHistory;
    private int highScore;

    public PlayerProfile(String playerName) 
    {
        this.playerName = playerName;
        this.scoreHistory = new ArrayList<>();
        this.highScore = 0;
    }

    public String getPlayerName() 
    {
        return playerName;
    }

    public void addScore(ScoreRecord record) 
    {
        scoreHistory.add(record);
        if (record.getScore() > highScore) 
        {
            highScore = record.getScore();
        }
    }

    public int getHighScore() 
    {
        return highScore;
    }

    public List<ScoreRecord> getScoreHistory() 
    {
        return new ArrayList<>(scoreHistory);
    }
}
