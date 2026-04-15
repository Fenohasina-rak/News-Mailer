package org.dev.Implementations;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.dev.Helpers.StringHelper;
import org.dev.Interfaces.NewsInterface;
import org.dev.Models.NewsModel;
import org.dev.RestClient.AlgoliaNewsRestClient;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Named("algolianews")
@ApplicationScoped
public class AlgoliaNewsImpl implements NewsInterface {
    private final String TAG = "story";
    private static final Integer MAX_NEWS = ConfigProvider.getConfig().getValue("reddit.max", Integer.class);
    AlgoliaNewsRestClient algoliaNewsRestClient;

    @Inject
    public AlgoliaNewsImpl(@RestClient AlgoliaNewsRestClient algoliaNewsRestClient) {
        this.algoliaNewsRestClient = algoliaNewsRestClient;
    }

    @Override
    public List<NewsModel> getNews(String keyword) {
        List<NewsModel> listNews = new ArrayList<>();
        String cleanedKeyword = StringHelper.cleanPathParam(keyword);
        JsonObject resultApi = this.algoliaNewsRestClient.fetchFromAlgolia(cleanedKeyword, TAG);
        JsonArray listHits = resultApi.getJsonArray("hits");
        IntStream.range(0,MAX_NEWS).forEach(iteration -> {
            NewsModel newsModel = new NewsModel();
            JsonObject data = listHits.getJsonObject(iteration);
            newsModel.setHeadline(data.getString("story_text") != null ? data.getString("story_text") : "");
            newsModel.setTitle(data.getString("title") != null ? data.getString("title") : "");
            newsModel.setUrl(data.getString("url") != null ? data.getString("url") : "");
            newsModel.setUpdatedAt(data.getString("updated_at") != null ? data.getString("updated_at") : "");
            listNews.add(newsModel);
        });

        return listNews;
    }
}
