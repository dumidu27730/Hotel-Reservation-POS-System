package edu.icet.controller.email;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailService {
    private final String senderEmail = "your-email@gmail.com"; // Change to your email
    private final String senderPassword = "emnf oznf sept ogpv"; // Use an App Password

    public String generateOTP() {
        Random random = new Random();
        int otp = 10000 + random.nextInt(90000); // Generate a 5-digit OTP
        return String.valueOf(otp);
    }

    public String sendOTP(String recipientEmail) {
        String otp = generateOTP();
        String subject = "Your OTP Code";
        String messageBody = "Your OTP code is: " + otp;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            System.out.println("OTP sent successfully to " + recipientEmail);
            return otp; // Return OTP for validation

        } catch (MessagingException e) {
            e.printStackTrace();
            return null; // Return null if email fails to send
        }
    }
}
