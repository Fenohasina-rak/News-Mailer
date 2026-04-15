package org.dev.RestClient;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "algolia-api")
public interface AlgoliaNewsRestClient {
    @GET
    @Path("/search_by_date")
    JsonObject fetchFromAlgolia(@QueryParam("query") String query , @QueryParam("tags") String tags);
}
