import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class StaffAppMySQL extends Application {

    private TextField tfID = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMI = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private TextField tfEmail = new TextField();

    private Label lblStatus = new Label();
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        connectDB();

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);

        // Row 0: ID
        pane.add(new Label("ID"), 0, 0);
        pane.add(tfID, 1, 0, 3, 1);

        // Row 1: Last Name / First Name / MI
        pane.add(new Label("Last Name"), 0, 1);
        pane.add(tfLastName, 1, 1);
        pane.add(new Label("First Name"), 2, 1);
        pane.add(tfFirstName, 3, 1);
        pane.add(new Label("MI"), 4, 1);
        tfMI.setPrefColumnCount(2);
        pane.add(tfMI, 5, 1);

        // Row 2: Address
        pane.add(new Label("Address"), 0, 2);
        pane.add(tfAddress, 1, 2, 5, 1);

        // Row 3: City / State
        pane.add(new Label("City"), 0, 3);
        pane.add(tfCity, 1, 3, 3, 1);
        pane.add(new Label("State"), 4, 3);
        tfState.setPrefColumnCount(2);
        pane.add(tfState, 5, 3);

        // Row 4: Telephone
        pane.add(new Label("Telephone"), 0, 4);
        pane.add(tfTelephone, 1, 4, 5, 1);

        // Row 5: Email (not shown in the figure but included in schema)
        pane.add(new Label("Email"), 0, 5);
        pane.add(tfEmail, 1, 5, 5, 1);

        // Row 6: Buttons
        Button btView = new Button("View");
        Button btInsert = new Button("Insert");
        Button btUpdate = new Button("Update");
        Button btClear = new Button("Clear");

        pane.add(btView, 0, 6);
        pane.add(btInsert, 1, 6);
        pane.add(btUpdate, 2, 6);
        pane.add(btClear, 3, 6);

        // Row 7: Status
        pane.add(lblStatus, 0, 7, 6, 1);

        btView.setOnAction(e -> viewRecord());
        btInsert.setOnAction(e -> insertRecord());
        btUpdate.setOnAction(e -> updateRecord());
        btClear.setOnAction(e -> clearFields());
        

        Scene scene = new Scene(pane, 550, 300);
        primaryStage.setTitle("ExtraExercise34_01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void connectDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/Staff"; // replace testdb with your DB name
            String user = "scott"; // replace with your username
            String password = "tiger"; // replace with your password

            connection = DriverManager.getConnection(url, user, password);
            lblStatus.setText("Database connected");
        } catch (SQLException e) {
            lblStatus.setText("Connection failed");
            e.printStackTrace();
        }
    }

    private void viewRecord() {
        String sql = "SELECT * FROM Staff WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tfID.getText().trim());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tfLastName.setText(rs.getString("lastName"));
                tfFirstName.setText(rs.getString("firstName"));
                tfMI.setText(rs.getString("mi"));
                tfAddress.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfTelephone.setText(rs.getString("telephone"));
                tfEmail.setText(rs.getString("email"));
                lblStatus.setText("Record found");
            } else {
                lblStatus.setText("Record not found");
            }
        } catch (SQLException e) {
            lblStatus.setText("Error viewing record");
        }
    }

    private void insertRecord() {
        String sql = "INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tfID.getText().trim());
            stmt.setString(2, tfLastName.getText().trim());
            stmt.setString(3, tfFirstName.getText().trim());
            stmt.setString(4, tfMI.getText().trim());
            stmt.setString(5, tfAddress.getText().trim());
            stmt.setString(6, tfCity.getText().trim());
            stmt.setString(7, tfState.getText().trim());
            stmt.setString(8, tfTelephone.getText().trim());
            stmt.setString(9, tfEmail.getText().trim());
            stmt.executeUpdate();
            lblStatus.setText("Record inserted");
        } catch (SQLException e) {
            lblStatus.setText("Error inserting record");
        }
    }

    private void updateRecord() {
        String sql = "UPDATE Staff SET lastName=?, firstName=?, mi=?, address=?, city=?, state=?, telephone=?, email=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tfLastName.getText().trim());
            stmt.setString(2, tfFirstName.getText().trim());
            stmt.setString(3, tfMI.getText().trim());
            stmt.setString(4, tfAddress.getText().trim());
            stmt.setString(5, tfCity.getText().trim());
            stmt.setString(6, tfState.getText().trim());
            stmt.setString(7, tfTelephone.getText().trim());
            stmt.setString(8, tfEmail.getText().trim());
            stmt.setString(9, tfID.getText().trim());
            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Record updated" : "Record not found");
        } catch (SQLException e) {
            lblStatus.setText("Error updating record");
        }
    }

    private void clearFields() {
        tfID.clear();
        tfLastName.clear();
        tfFirstName.clear();
        tfMI.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfTelephone.clear();
        tfEmail.clear();
        lblStatus.setText("Fields cleared");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
