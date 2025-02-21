package edu.icet.controller;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailOtp {

    private static final String SENDER_EMAIL = "mjd2003sachinthaka@gmail.com";
    private static final String APP_PASSWORD = "jfql vtir mrww rvar";

    public static String sendOTP(String recipientEmail) {
        String otp = generateOTP(); // Generate 6-digit OTP

        // Email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, APP_PASSWORD);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            // Send email
            Transport.send(message);
            System.out.println("OTP sent successfully to " + recipientEmail);
            return otp; // Return OTP to verify later
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static void main(String[] args) {
        String testEmail = "ravindu.dev4166@gmail.com";
        String otp = sendOTP(testEmail);
        System.out.println("Generated OTP: " + otp);
    }
}