import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleColorChange extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(100, Color.WHITE);
        circle.setStroke(Color.GRAY);
        circle.setStrokeWidth(4); 

        circle.setOnMousePressed(e -> circle.setFill(Color.BLACK));
        circle.setOnMouseReleased(e -> circle.setFill(Color.WHITE));

        StackPane root = new StackPane(circle);
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Circle Color Change");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}