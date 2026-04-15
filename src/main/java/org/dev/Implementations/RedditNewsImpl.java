package org.dev.Implementations;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.dev.Helpers.StringHelper;
import org.dev.Interfaces.NewsInterface;
import org.dev.Models.NewsModel;
import org.dev.RestClient.RedditRestClient;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Named("reddit")
@ApplicationScoped
public class RedditNewsImpl implements NewsInterface {
    private static final String BASE_URL = ConfigProvider.getConfig().getValue("reddit.baseurl", String.class);
    private static final Integer MAX_NEWS = ConfigProvider.getConfig().getValue("reddit.max", Integer.class);
    RedditRestClient redditRestClient;
    public RedditNewsImpl(@RestClient RedditRestClient redditRestClient) {
        this.redditRestClient = redditRestClient;
    }

    @Override
    public List<NewsModel> getNews(String keyword) {
        List<NewsModel> listNews = new ArrayList<>();
        String cleanedKeyword = StringHelper.cleanPathParam(keyword);
        JsonObject result = this.redditRestClient.fetchFromReddit(cleanedKeyword);

        IntStream.range(0,MAX_NEWS).forEach(iteration -> {
            NewsModel newsModel = new NewsModel();
            JsonObject data = result.getJsonObject("data").getJsonArray("children").getJsonObject(iteration).getJsonObject("data");
            newsModel.setTitle(data.getString("title") != null ? data.getString("title") : "");
            listNews.add(newsModel);
        });

        return listNews;
    }



}
