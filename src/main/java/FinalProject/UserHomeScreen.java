package FinalProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the user's home screen. Displays player's name,
 * high score, and score history, and allows navigation.
 */
public class UserHomeScreen {

    private Stage stage;
    private Scene scene;
    private PlayerProfile playerProfile;

    private Runnable onPlayAgain;
    private Runnable onMainMenu;

    public UserHomeScreen(Stage stage, PlayerProfile profile, Runnable onPlayAgain, Runnable onMainMenu) {
        this.stage = stage;
        this.playerProfile = profile;
        this.onPlayAgain = onPlayAgain;
        this.onMainMenu = onMainMenu;
        this.scene = createScene();
    }

    private Scene createScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Top: User information
        Label title = new Label("User Home");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        VBox userInfoBox = new VBox(10);
        userInfoBox.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label("Player: " + playerProfile.getPlayerName());
        nameLabel.setStyle("-fx-font-size: 16px;");

        Label highScoreLabel = new Label("High Score: " + playerProfile.getHighScore());
        highScoreLabel.setStyle("-fx-font-size: 16px;");

    // Display current score if available
    int currentScore = playerProfile.getScoreHistory().isEmpty() ? 0 : playerProfile.getScoreHistory().get(playerProfile.getScoreHistory().size() - 1).getScore();
    Label currentScoreLabel = new Label("Current Score: " + currentScore);
    currentScoreLabel.setStyle("-fx-font-size: 16px;");

    userInfoBox.getChildren().addAll(nameLabel, highScoreLabel, currentScoreLabel);

        // Center: Score history
        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER_LEFT);
        Label historyTitle = new Label("Score History:");
        historyTitle.setStyle("-fx-font-size: 18px; -fx-underline: true;");

        ListView<String> scoreList = new ListView<>();
        for (ScoreRecord record : playerProfile.getScoreHistory()) {
            String item = "Score: " + record.getScore() +
                          " | Difficulty: " + difficultyLabel(record.getDifficulty());
            scoreList.getItems().add(item);
        }
        scoreList.setPrefHeight(200);

        centerBox.getChildren().addAll(historyTitle, scoreList);

        // Bottom: Navigation buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        Button playButton = new Button("Play Again");
        Button mainMenuButton = new Button("Main Menu");
        Button exitButton = new Button("Exit");

        playButton.setOnAction(e -> {
            if (onPlayAgain != null) onPlayAgain.run();
        });

        mainMenuButton.setOnAction(e -> {
            if (onMainMenu != null) onMainMenu.run();
        });

        exitButton.setOnAction(e -> stage.close());

        buttonBox.getChildren().addAll(playButton, mainMenuButton, exitButton);

        // Assemble layout
        root.setLeft(userInfoBox);
        root.setCenter(centerBox);
        root.setBottom(buttonBox);

        return new Scene(root, 600, 400);
    }

    private String difficultyLabel(int difficulty) {
        return switch (difficulty) {
            case 1 -> "Easy";
            case 2 -> "Medium";
            case 3 -> "Hard";
            default -> "Unknown";
        };
    }

    public Scene getScene() {
        return scene;
    }
}
