package org.dev.RestClient;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.dev.Models.NewsModel;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "reddit-api")
public interface RedditRestClient {
    @GET
    @Path("/{keyword}/new.json")
    JsonObject fetchFromReddit(@PathParam("keyword") String keyword);
}
