package org.dev.RestClient;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.dev.Models.Brevo.SendEmailBody;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "brevo-api")
@ClientHeaderParam(name = "api-key", value = "${brevo.mailer.api.key}")
public interface BrevoRestClient {
    @POST
    @Path("/smtp/email")
    JsonObject sendEmail(SendEmailBody emailBody);
}
