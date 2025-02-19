package edu.icet.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import edu.icet.controller.email.EmailService;
import edu.icet.db.DBConnection;
import edu.icet.dto.User;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.UserService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ForgotPasswordFormController implements Initializable {

    @FXML
    private JFXButton btnChangePassword;

    @FXML
    private JFXButton btnSend;

    @FXML
    private Label lblOtpSent;

    @FXML
    private Label txtMessage;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private TextField txtOTPFive;

    @FXML
    private TextField txtOTPFour;

    @FXML
    private TextField txtOTPOne;

    @FXML
    private TextField txtOTPThree;

    @FXML
    private TextField txtOTPTwo;

    private final EmailService emailService = new EmailService();
    UserService service = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    private String generatedOTP;

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) throws SQLException {
//        String newPassword = txtNewPassword.getText();
//        String confirmPassword = txtConfirmPassword.getText();
//
//      // if (newPassword.equals(confirmPassword)) {
//
//            if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
//                String Key = "1234";
//
//                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
//                basicTextEncryptor.setPassword(Key);
//
//
//                String SQL = "SELECT * FROM users WHERE email ='" + txtEmail.getText() + "'";
//
//                    Connection connection = DBConnection.getInstance().getConnection();
//                    ResultSet resultSet = connection.createStatement().executeQuery(SQL);
//                try {
//                    if (resultSet.next()) {
//                        User user = new User(
//                                resultSet.getInt(1),
//                                resultSet.getString(2),
//                                resultSet.getString(3),
//                                resultSet.getString(4),
//                                resultSet.getString(5),
//                                resultSet.getString(6),
//                                resultSet.getString(7),
//                                resultSet.getString(8)
//                        );
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//            }else{
//
//            }
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        lblOtpSent.setVisible(true);

        String email = txtEmail.getText();
        if (email.isEmpty()) {

            Alert.AlertType warning = Alert.AlertType.WARNING;
            return;
        }

        generatedOTP = emailService.sendOTP(email);
        System.out.println(generatedOTP);
        if (generatedOTP != null) {
            lblOtpSent.setText("OTP Sent Successfully!");
            lblOtpSent.setStyle("-fx-text-fill: green;");
            txtMessage.setVisible(true);
        } else {

        }
    }

    @FXML
    void txtOTPFiveOnAction(ActionEvent event) {
        String enteredOTP = txtOTPOne.getText() + txtOTPTwo.getText() + txtOTPThree.getText() + txtOTPFour.getText() + txtOTPFive.getText();

        if (enteredOTP.equals(generatedOTP)) {
            txtMessage.setText("OTP Verified ✅");
            txtMessage.setStyle("-fx-text-fill: green;");
            lblOtpSent.setVisible(false);

            txtNewPassword.setDisable(false);
            txtConfirmPassword.setDisable(false);
            btnChangePassword.setDisable(false);
        } else {
            txtMessage.setText("Invalid OTP ❌");
            txtMessage.setStyle("-fx-text-fill: red;");
            lblOtpSent.setVisible(false);
        }
    }

    public void setTextFieldLimit(TextField textField, int maxLength) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                textField.setText(oldValue);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextFieldLimit(txtOTPOne, 1);
        setTextFieldLimit(txtOTPTwo, 1);
        setTextFieldLimit(txtOTPThree, 1);
        setTextFieldLimit(txtOTPFour, 1);
        setTextFieldLimit(txtOTPFive, 1);
    }
}