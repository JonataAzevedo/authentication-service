package com.infodev.auth.application.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties("auth.service")
public class PropertyConfig {

    private final Environment environment = new Environment();
    private final Register register = new Register();
    private final Login login = new Login();
    private final Login home = new Login();


    @Getter
    @Setter
    public static class Environment {
        private String url;
    }

    @Getter
    @Setter
    public static class Register {
        private String url;
    }

    @Getter
    @Setter
    public static class Login {
        private String url;
    }

    @Getter
    @Setter
    public static class Home {
        private String url;
    }
}
