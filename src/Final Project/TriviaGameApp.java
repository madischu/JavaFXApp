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

    private Stage primaryStage;

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

    /**
     * Creates the Main Menu scene with three side-by-side difficulty buttons.
     */
    private Scene createMainMenuScene() {
        Label title = new Label("Trivia Game");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Difficulty buttons (side by side)
        Button easyBtn = new Button("Easy");
        Button mediumBtn = new Button("Medium");
        Button hardBtn = new Button("Hard");

        easyBtn.setPrefWidth(100);
        mediumBtn.setPrefWidth(100);
        hardBtn.setPrefWidth(100);

        // Event example 1: Start game with chosen difficulty
        easyBtn.setOnAction(e -> startGame("Easy"));
        mediumBtn.setOnAction(e -> startGame("Medium"));
        hardBtn.setOnAction(e -> startGame("Hard"));

        HBox difficultyBox = new HBox(20, easyBtn, mediumBtn, hardBtn);
        difficultyBox.setAlignment(Pos.CENTER);

        // Extra buttons
        Button homeBtn = new Button("User Home");
        Button instructionsBtn = new Button("Instructions");

        // Event example 2: Show instructions
        instructionsBtn.setOnAction(e -> primaryStage.setScene(createInstructionsScene()));

        VBox mainBox = new VBox(20, title, difficultyBox, homeBtn, instructionsBtn);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(40));

        BorderPane root = new BorderPane();
        root.setCenter(mainBox);

        return new Scene(root, 500, 400);
    }

    /**
     * Creates a placeholder Game Scene based on selected difficulty.
     */
    private Scene createGameScene(String difficulty) {
        Label label = new Label("Playing " + difficulty + " Level...");
        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));

        VBox layout = new VBox(20, label, backBtn);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 500, 400);
    }

    /**
     * Creates the Instructions Scene.
     */
    private Scene createInstructionsScene() {
        Label label = new Label("Instructions:\nAnswer questions to gain points.\nSelect difficulty to start.");
        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));

        VBox layout = new VBox(20, label, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        return new Scene(layout, 500, 400);
    }

    /**
     * Navigates to the game scene based on chosen difficulty.
     */
    private void startGame(String difficulty) {
        primaryStage.setScene(createGameScene(difficulty));
    }
}