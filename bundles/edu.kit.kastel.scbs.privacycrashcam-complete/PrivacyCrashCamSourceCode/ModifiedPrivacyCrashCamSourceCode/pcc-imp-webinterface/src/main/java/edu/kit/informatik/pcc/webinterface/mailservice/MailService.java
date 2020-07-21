package edu.kit.informatik.pcc.webinterface.mailservice;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by chris on 17.01.2017.
 *
 * This class has the method to send an email and to check if an given mail is a legit mail.
 */
public class MailService {

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /**
     * The method to send an email. It gets the SMTP properties from mail properties file.
     *
     * @param from    from mail
     * @param to      to mail
     * @param subject subject line
     * @param text    mail body
     * @throws EmailException
     * @throws IOException
     */
    public static void send(String from, String to, String subject, String text)
            throws EmailException, IOException {

        // check for null references
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        // load email configuration from properties file
        Properties properties = new Properties();
        properties.load(MailService.class.getResourceAsStream("/mail.properties"));
        String host = properties.getProperty("mail.smtp.host");
        String port = properties.getProperty("mail.smtp.port");
        String ssl = properties.getProperty("mail.smtp.ssl.enable");
        String username = properties.getProperty("mail.smtp.username");
        String password = properties.getProperty("mail.smtp.password");

        // create an email message with html support
        HtmlEmail email = new HtmlEmail();

        // configure SMTP connection
        email.setHostName(host);
        email.setSmtpPort(Integer.parseInt(port));
        email.setAuthentication(username, password);
        email.setSSLOnConnect(Boolean.parseBoolean(ssl));

        // set its properties accordingly
        email.setFrom(from);
        email.addTo(to);
        email.setSubject(subject);
        email.setHtmlMsg(text);

        // send it!
        //email.send();
	System.out.println(text);
    }
}
