package FinalProject;

/**
 * Abstract class representing a trivia question.
 */
public abstract class Question {
    private String questionText;
    private String[] options;
    private String correctAnswer;
    private String category;
    private int difficulty;
    private String userAnswer; // stores the user's selected answer

    public Question(String questionText, String[] options, String correctAnswer, String category, int difficulty) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public abstract boolean checkAnswer(String answer);
}
