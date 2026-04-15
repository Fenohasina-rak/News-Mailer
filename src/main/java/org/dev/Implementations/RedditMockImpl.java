package org.dev.Implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.dev.Interfaces.NewsInterface;
import org.dev.Models.NewsModel;

import java.util.List;

@Named("newsMocked")
@ApplicationScoped
public class RedditMockImpl implements NewsInterface {
    @Override
    public List<NewsModel> getNews(String keyword) {
        return List.of();
    }
}
