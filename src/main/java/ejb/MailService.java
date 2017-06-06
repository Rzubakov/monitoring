/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class MailService {

    @Resource(name = "gmail")
    Session session;

    public MailService() {
    }
    

    public void send(String addresses, String topic, String textMessage) {

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);

            Transport.send(message);
            System.out.println("sending");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
