package idlerpg;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.client.JerseyClientConfiguration;

public class IdleRPGConfiguration extends Configuration {
    @Valid
    @NotNull
    private JerseyClientConfiguration httpClientConfig = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return httpClientConfig;
    }

    @NotEmpty
    private String token;

    @JsonProperty
    public String getToken() {
        return token;
    }
}
