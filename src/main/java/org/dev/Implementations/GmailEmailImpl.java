package org.dev.Implementations;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.dev.Interfaces.MailerInterface;
@Named("gmail")
@ApplicationScoped
public class GmailEmailImpl implements MailerInterface {

    ReactiveMailer mailer;
    @Inject
    public GmailEmailImpl(ReactiveMailer mailer) {
        this.mailer = mailer;
    }

    @Override
    public void sendEmail(String receiver, String content) {
        mailer.send(
                Mail.withText(receiver, "News Update", "tests")
        ).subscribeAsCompletionStage().join();
    }
}
