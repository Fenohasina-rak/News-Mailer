package org.dev.Implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.dev.Interfaces.MailerInterface;

@Named("mockedMail")
@ApplicationScoped
public class EmailImpl implements MailerInterface {
    @Override
    public void sendEmail(String receiver, String content) {
        System.out.println("Email Sent");
    }
}
