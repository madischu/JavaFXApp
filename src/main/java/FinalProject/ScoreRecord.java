package FinalProject;

/**
 * Represents a single game score, along with the difficulty level.
 */
public class ScoreRecord 
{
    private int score;
    private int difficulty; // 1 = Easy, 2 = Medium, 3 = Hard

    public ScoreRecord(int score, int difficulty) 
    {
        this.score = score;
        this.difficulty = difficulty;
    }

    public int getScore() 
    {
        return score;
    }

    public int getDifficulty() 
    {
        return difficulty;
    }
}
