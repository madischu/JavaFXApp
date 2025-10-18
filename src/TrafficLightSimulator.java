import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TrafficLightSimulator extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Circles for lights
        Circle redLight = new Circle(20, Color.WHITE);
        redLight.setStroke(Color.BLACK);
        Circle yellowLight = new Circle(20, Color.WHITE);
        yellowLight.setStroke(Color.BLACK);
        Circle greenLight = new Circle(20, Color.WHITE);
        greenLight.setStroke(Color.BLACK);

        VBox lightsBox = new VBox(10, redLight, yellowLight, greenLight);
        lightsBox.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-border-color: black; -fx-border-width: 2;");

        // Radio buttons
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbGreen = new RadioButton("Green");

        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbYellow.setToggleGroup(group);
        rbGreen.setToggleGroup(group);

        HBox buttonsBox = new HBox(20, rbRed, rbYellow, rbGreen);
        buttonsBox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Event handlers
        rbRed.setOnAction(e -> {
            redLight.setFill(Color.RED);
            yellowLight.setFill(Color.WHITE);
            greenLight.setFill(Color.WHITE);
        });
        rbYellow.setOnAction(e -> {
            redLight.setFill(Color.WHITE);
            yellowLight.setFill(Color.YELLOW);
            greenLight.setFill(Color.WHITE);
        });
        rbGreen.setOnAction(e -> {
            redLight.setFill(Color.WHITE);
            yellowLight.setFill(Color.WHITE);
            greenLight.setFill(Color.GREEN);
        });

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(lightsBox);
        root.setBottom(buttonsBox);

        Scene scene = new Scene(root, 250, 300);
        primaryStage.setTitle("Exercise16_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}