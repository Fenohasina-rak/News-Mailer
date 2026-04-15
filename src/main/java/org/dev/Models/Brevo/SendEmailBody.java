package org.dev.Models.Brevo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendEmailBody {
    @JsonProperty("sender")
    private Sender sender;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("textContent")
    private String textContent;
    @JsonProperty("to")
    private List<EmailUser> receivers;
    @JsonProperty("params")
    private Params params;
    @JsonProperty("htmlContent")
    private String htmlContent;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public List<EmailUser> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<EmailUser> receivers) {
        this.receivers = receivers;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
