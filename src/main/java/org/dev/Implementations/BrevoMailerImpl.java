package org.dev.Implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.dev.Interfaces.MailerInterface;
import org.dev.Models.Brevo.EmailUser;
import org.dev.Models.Brevo.Params;
import org.dev.Models.Brevo.SendEmailBody;
import org.dev.Models.Brevo.Sender;
import org.dev.RestClient.BrevoRestClient;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("brevomailer")
@ApplicationScoped
public class BrevoMailerImpl implements MailerInterface {
    private static final String SENDER_EMAIL = ConfigProvider.getConfig().getValue("mailer.username", String.class);
    private static final String DISPLAY_NAME = ConfigProvider.getConfig().getValue("mailer.displayname", String.class);

    BrevoRestClient brevoRestClient;

    public BrevoMailerImpl(@RestClient BrevoRestClient brevoRestClient) {
        this.brevoRestClient = brevoRestClient;
    }

    @Override
    public void sendEmail(String receiver, String content) {
        Date currentDate = new Date();
        SendEmailBody body = new SendEmailBody();
        Sender sender = new Sender(SENDER_EMAIL);
        List<EmailUser> listReceivers = new ArrayList<>();
        listReceivers.add(new EmailUser(receiver));
        sender.setName(DISPLAY_NAME);
        body.setSubject("News from today:" + currentDate);
        body.setSender(sender);
        body.setTextContent(content);
        body.setReceivers(listReceivers);
        brevoRestClient.sendEmail(body);
    }
}
