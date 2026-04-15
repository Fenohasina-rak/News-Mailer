package org.dev.Services;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.dev.Interfaces.MailerInterface;
import org.dev.Interfaces.NewsInterface;
import org.dev.Models.NewsModel;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.List;


@ApplicationScoped
public class NewsService {
    private static final String RECEIVER_EMAIL = ConfigProvider.getConfig().getValue("receiver.username", String.class);
    NewsInterface newsInterface;
    MailerInterface mailerInterface;
    Template newsTemplate;
    @Inject
    public NewsService(@Named("algolianews")NewsInterface newsInterface,@Named("brevomailer")MailerInterface mailerInterface, @Location("news-template.html")Template newsTemplate ) {
        this.newsInterface = newsInterface;
        this.mailerInterface = mailerInterface;
        this.newsTemplate = newsTemplate;
    }

    public  void sendNews(String keyword){
        List<NewsModel> listNews = this.newsInterface.getNews(keyword);
        String finalHtmlString = newsTemplate
                .data("stories", listNews)
                .render();
        this.mailerInterface.sendEmail(RECEIVER_EMAIL, finalHtmlString);
    }


}
