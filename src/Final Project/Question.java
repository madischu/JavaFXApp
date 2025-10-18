/**
 * Abstract class representing a trivia question.
 * Provides base fields and an abstract method for checking answers.
 */
public abstract class Question {
    private String questionText;
    private String[] options;
    private String correctAnswer;
    private String category;
    private int difficulty; // 1 = Easy, 2 = Medium, 3 = Hard

    public Question(String questionText, String[] options, String correctAnswer, String category, int difficulty) 
    {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getQuestionText() 
    {
        return questionText;
    }

    public String[] getOptions() 
    {
        return options;
    }

    public String getCorrectAnswer() 
    {
        return correctAnswer;
    }

    public String getCategory() 
    {
        return category;
    }

    public int getDifficulty() 
    {
        return difficulty;
    }

    /**
     * Abstract method to check if the given answer is correct.
     * @param answer The answer chosen by the user.
     * @return true if correct, false otherwise.
     */
    public abstract boolean checkAnswer(String answer);
}
