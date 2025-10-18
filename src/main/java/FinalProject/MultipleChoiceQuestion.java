package FinalProject;

/**
 * A multiple-choice trivia question that includes an explanation.
 */
public class MultipleChoiceQuestion extends Question {
    private String explanation;

    public MultipleChoiceQuestion(String questionText, String[] options, String correctAnswer,
                                  String category, int difficulty, String explanation) {
        super(questionText, options, correctAnswer, category, difficulty);
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return getCorrectAnswer().equalsIgnoreCase(answer);
    }
}
