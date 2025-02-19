package edu.icet.controller;

import edu.icet.db.DBConnection;
import edu.icet.dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane anchorPaneLogin;
    @FXML
    private ComboBox<String> cmbPosition;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    HomeFormController homeFormController = new HomeFormController();

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {

            String key = "1234";
            BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
            basicTextEncryptor.setPassword(key);

            String SQL = "SELECT * FROM users WHERE position ='" + cmbPosition.getValue() + "' AND username='" + txtUsername.getText() + "'";
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                ResultSet resultSet = connection.createStatement().executeQuery(SQL);
                if (resultSet.next()) {
                    User user = new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    );

                    if (basicTextEncryptor.decrypt(user.getPassword()).equals(txtPassword.getText())) {
                        System.out.println("Login Successful!");

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../view/home.fxml"));
                        Parent root = loader.load();
                        HomeFormController homeFormController = loader.getController();
                        homeFormController.getUserId(user); // Pass user info to HomeFormController

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Home");
                        stage.show();

                        anchorPaneLogin.getScene().getWindow().hide();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "User Not Found!").show();
                }
            } catch (SQLException | IOException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }








//        String Key="1234";
//
//        BasicTextEncryptor basicTextEncryptor= new BasicTextEncryptor();
//        basicTextEncryptor.setPassword(Key);
//
//        String SQL = "SELECT * FROM users WHERE position ='" + cmbPosition.getValue()+ "' AND   username=" + "'" + txtUsername.getText() + "'";
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
//            resultSet.next();
//            User user = new User(
//                    resultSet.getInt(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getString(8)
//            );
//
//            System.out.println(user);
//            if (user != null) {
//                homeFormController.getUserId(user);
//                if (basicTextEncryptor.decrypt(user.getPassword()).equals(txtPassword.getText())) {
//                    System.out.println("Login!");
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../view/home.fxml"))));
//                    stage.show();
//                    anchorPaneLogin.getScene().getWindow().hide();
//
//                } else {
//                    new Alert(Alert.AlertType.ERROR,"Check your Password").show();
//                    System.out.println("Check your password");
//                }
//            } else {
//                new Alert(Alert.AlertType.ERROR,"User Not Found").show();
//                System.out.println("user Not found!");
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }


    public void textForgotPasswordOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../view/forgot_password_form.fxml"))));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> position = FXCollections.observableArrayList();
        position.add("Admin");
        position.add("Receptionist");
        cmbPosition.setItems(position);
    }



}
