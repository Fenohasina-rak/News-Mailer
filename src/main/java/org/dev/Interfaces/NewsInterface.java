package org.dev.Interfaces;

import org.dev.Models.NewsModel;

import java.util.List;

public interface NewsInterface {
    public List<NewsModel> getNews(String keyword);
}
