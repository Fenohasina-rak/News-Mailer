package org.dev.Services;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class MainJob implements QuarkusApplication {

    @Inject
    NewsService newsService;

    @Override
    public int run(String... args) throws Exception {
        newsService.sendNews("java");
        newsService.sendNews("quarkus");
        return 0;
    }
}
