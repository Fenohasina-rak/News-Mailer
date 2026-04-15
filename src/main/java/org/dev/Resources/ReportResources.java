package org.dev.Resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.dev.Services.NewsService;

@Path("/api")
public class ReportResources {
    NewsService newsService;

    @Inject
    public ReportResources(NewsService newsService) {
        this.newsService = newsService;
    }


    @Path("/sendnews/{keyword}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void sendNews(@PathParam("keyword") String keyword) {
        this.newsService.sendNews(keyword);
    }
}
