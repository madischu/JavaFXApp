package FinalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the trivia game logic, including questions,
 * scoring, and interaction with the PlayerProfile.
 */
public class GameManager 
{
    private List<Question> questions;
    private int currentScore;
    private int currentIndex;
    private PlayerProfile playerProfile;
    private int maxQuestions;

    public GameManager(PlayerProfile playerProfile, int maxQuestions) 
    {
        this.playerProfile = playerProfile;
        this.maxQuestions = maxQuestions;
        this.questions = new ArrayList<>();
        this.currentScore = 0;
        this.currentIndex = 0;
    }

    public void loadQuestions(List<Question> questionBank) 
    {
        Collections.shuffle(questionBank); // randomize order
        this.questions = questionBank.subList(0, Math.min(maxQuestions, questionBank.size()));
    }

    public void startGame() 
    {
        currentScore = 0;
        currentIndex = 0;
    }

    public Question nextQuestion() 
    {
        if (currentIndex < questions.size()) 
        {
            return questions.get(currentIndex++);
        }
        return null;
    }

    public void updateScore(boolean correct) 
    {
        if (correct) 
        {
            currentScore++;
        }
    }

    public void showResults(int difficulty) 
    {
        ScoreRecord record = new ScoreRecord(currentScore, difficulty);
        playerProfile.addScore(record);
    }

    public int getCurrentScore() 
    {
        return currentScore;
    }

    public int getMaxQuestions() 
    {
        return maxQuestions;
    }
}
