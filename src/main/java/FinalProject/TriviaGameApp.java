package FinalProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main JavaFX application class for the Trivia Game.
 * Displays the main menu with difficulty options and navigates between screens.
 */

public class TriviaGameApp extends Application {
    private int reviewIndex = 0;
    private Stage primaryStage;
    private GameManager gameManager;
    private String currentDifficulty;
    private PlayerProfile playerProfile = new PlayerProfile("Player1");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Trivia Game");
        primaryStage.setScene(createMainMenuScene());
        primaryStage.show();
    }

    private Scene createMainMenuScene() {
        Label title = new Label("Trivia Game");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button easyBtn = new Button("Easy");
        Button mediumBtn = new Button("Medium");
        Button hardBtn = new Button("Hard");

        easyBtn.setPrefWidth(100);
        mediumBtn.setPrefWidth(100);
        hardBtn.setPrefWidth(100);

        easyBtn.setOnAction(e -> startGame("Easy"));
        mediumBtn.setOnAction(e -> startGame("Medium"));
        hardBtn.setOnAction(e -> startGame("Hard"));

        HBox difficultyBox = new HBox(20, easyBtn, mediumBtn, hardBtn);
        difficultyBox.setAlignment(Pos.CENTER);

        Button homeBtn = new Button("User Home");
        Button instructionsBtn = new Button("Instructions");
        instructionsBtn.setOnAction(e -> primaryStage.setScene(createInstructionsScene()));

        homeBtn.setOnAction(e -> {
            UserHomeScreen homeScreen = new UserHomeScreen(
                primaryStage,
                playerProfile,
                () -> startGame(currentDifficulty != null ? currentDifficulty : "Easy"),
                () -> primaryStage.setScene(createMainMenuScene())
            );
            primaryStage.setScene(homeScreen.getScene());
        });

        VBox mainBox = new VBox(20, title, difficultyBox, homeBtn, instructionsBtn);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(40));

        BorderPane root = new BorderPane();
        root.setCenter(mainBox);

        return new Scene(root, 500, 400);
    }

    private Scene createGameScene() {
        Question question = gameManager.getNextQuestion();
        if (question == null) {
            int difficultyNum = 1;
            if ("Medium".equalsIgnoreCase(currentDifficulty)) difficultyNum = 2;
            else if ("Hard".equalsIgnoreCase(currentDifficulty)) difficultyNum = 3;
            playerProfile.addScore(new ScoreRecord(gameManager.getScore(), difficultyNum));

            // Show post-game menu with options
            return createPostGameMenuScene();
        }



        Label questionLabel = new Label(question.getQuestionText());
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 18px;");

        Button[] optionButtons = new Button[question.getOptions().length];
        VBox optionsBox = new VBox(10);
        optionsBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < question.getOptions().length; i++) {
            String option = question.getOptions()[i];
            optionButtons[i] = new Button(option);
            optionButtons[i].setPrefWidth(200);
            optionButtons[i].setOnAction(e -> {
                boolean correct = question.checkAnswer(option);
                if (correct) {
                    gameManager.incrementScore();
                }
                primaryStage.setScene(createGameScene());
            });
            optionsBox.getChildren().add(optionButtons[i]);
        }

        Label scoreLabel = new Label("Score: " + gameManager.getScore());
        scoreLabel.setStyle("-fx-font-size: 14px;");

        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));

        VBox layout = new VBox(20, questionLabel, optionsBox, scoreLabel, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        return new Scene(layout, 500, 400);
    }



    // REVIEW SCREEN
    private Scene createReviewScene() {
        java.util.List<Question> questions = gameManager.getQuestions();
        if (questions == null || questions.isEmpty()) {
            Label noQuestions = new Label("No questions to review.");
            VBox box = new VBox(20, noQuestions);
            box.setAlignment(Pos.CENTER);
            return new Scene(box, 500, 400);
        }

        if (reviewIndex >= questions.size()) reviewIndex = 0;
        Question q = questions.get(reviewIndex);

        VBox qLayout = new VBox(20);
        qLayout.setAlignment(Pos.CENTER);
        qLayout.setPadding(new Insets(30));

        Label title = new Label("Review Your Answers");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        qLayout.getChildren().add(title);

        Label questionLabel = new Label(q.getQuestionText());
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 18px;");
        qLayout.getChildren().add(questionLabel);

        Button[] optionButtons = new Button[q.getOptions().length];
        VBox optionsBox = new VBox(10);
        optionsBox.setAlignment(Pos.CENTER);

        String userAns = q.getUserAnswer();
        String correctAns = q.getCorrectAnswer();

        for (int i = 0; i < q.getOptions().length; i++) {
            String option = q.getOptions()[i];
            optionButtons[i] = new Button(option);
            optionButtons[i].setPrefWidth(200);
            optionButtons[i].setDisable(true);
            // Highlight user's answer
            if (userAns != null && userAns.equals(option)) {
                if (userAns.equals(correctAns)) {
                    optionButtons[i].setStyle("-fx-background-color: #b6e7a6; -fx-font-weight: bold;"); // green
                } else {
                    optionButtons[i].setStyle("-fx-background-color: #f7b6b0; -fx-font-weight: bold;"); // red
                }
            } else if (option.equals(correctAns)) {
                optionButtons[i].setStyle("-fx-background-color: #d4f7c5;"); // light green for correct
            }
            optionsBox.getChildren().add(optionButtons[i]);
        }
        qLayout.getChildren().add(optionsBox);

        // Explanation at the bottom
        Label explLabel = null;
        if (q instanceof MultipleChoiceQuestion) {
            String expl = ((MultipleChoiceQuestion)q).getExplanation();
            if (expl != null && !expl.isEmpty()) {
                explLabel = new Label("Explanation: " + expl);
                explLabel.setStyle("-fx-font-style: italic; -fx-text-fill: #555;");
            }
        }
        if (explLabel != null) qLayout.getChildren().add(explLabel);

        Button nextBtn = new Button(reviewIndex < questions.size() - 1 ? "Next" : "Done");
        nextBtn.setPrefWidth(120);
        nextBtn.setOnAction(e -> {
            if (reviewIndex < questions.size() - 1) {
                reviewIndex++;
                primaryStage.setScene(createReviewScene());
            } else {
                reviewIndex = 0;
                primaryStage.setScene(createPostGameMenuScene());
            }
        });
        qLayout.getChildren().add(nextBtn);

        return new Scene(qLayout, 600, 500);
    }

    private Scene createInstructionsScene() {
        Label label = new Label("Instructions:\nSelect difficulty to start.\nAnswer questions to gain points.");
        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));

        VBox layout = new VBox(20, label, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        return new Scene(layout, 500, 400);
    }

    private void startGame(String difficulty) {
        this.currentDifficulty = difficulty;
        this.gameManager = new GameManager();
        this.gameManager.loadQuestionsForDifficulty(difficulty);
        primaryStage.setScene(createGameScene());
    }

    // POST-GAME MENU
    private Scene createPostGameMenuScene() {
        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(40));

        Label endLabel = new Label("Game Over! Your score: " + gameManager.getScore());
        endLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button reviewBtn = new Button("Review Questions");
        reviewBtn.setPrefWidth(200);
        reviewBtn.setOnAction(e -> primaryStage.setScene(createReviewScene()));

        Button playAgainBtn = new Button("Play Again");
        playAgainBtn.setPrefWidth(200);
        playAgainBtn.setOnAction(e -> startGame(currentDifficulty));

        Button mainMenuBtn = new Button("Main Menu");
        mainMenuBtn.setPrefWidth(200);
        mainMenuBtn.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));

        Button userHomeBtn = new Button("User Home");
        userHomeBtn.setPrefWidth(200);
        userHomeBtn.setOnAction(e -> {
            UserHomeScreen homeScreen = new UserHomeScreen(
                primaryStage,
                playerProfile,
                () -> startGame(currentDifficulty != null ? currentDifficulty : "Easy"),
                () -> primaryStage.setScene(createMainMenuScene())
            );
            primaryStage.setScene(homeScreen.getScene());
        });

        menuBox.getChildren().addAll(endLabel, reviewBtn, playAgainBtn, mainMenuBtn, userHomeBtn);
        return new Scene(menuBox, 500, 400);
    }
}
