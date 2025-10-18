import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DisplayImages extends Application {

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new javafx.geometry.Insets(5));

        // Four local image files from C:/Users/madis/Downloads/14-1 Images/Images
        ImageView flag1 = new ImageView(new Image("file:C:/Users/madis/Downloads/14-1 Images/Images/flag1.gif"));
        ImageView flag2 = new ImageView(new Image("file:C:/Users/madis/Downloads/14-1 Images/Images/flag2.gif"));
        ImageView flag3 = new ImageView(new Image("file:C:/Users/madis/Downloads/14-1 Images/Images/flag3.gif"));
        ImageView flag4 = new ImageView(new Image("file:C:/Users/madis/Downloads/14-1 Images/Images/flag4.gif"));
        
        // Set flag sizes to fill grid cells
        flag1.setFitWidth(180); flag1.setFitHeight(90);
        flag2.setFitWidth(180); flag2.setFitHeight(90);
        flag3.setFitWidth(180); flag3.setFitHeight(90);
        flag4.setFitWidth(180); flag4.setFitHeight(90);

        // Arrange flags: flag4 (Chinese) above flag1 (American)
        grid.add(flag2, 0, 0); // Top-left (German)
        grid.add(flag4, 1, 0); // Top-right (Chinese)
        grid.add(flag3, 0, 1); // Bottom-left (French)
        grid.add(flag1, 1, 1); // Bottom-right (American)


        Scene scene = new Scene(grid, 400, 300);
        stage.setTitle("4-image GridPane");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}