package idlerpg;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import idlerpg.resources.HelloScalaResource;
import idlerpg.health.TelegramAPICheck;

import com.fasterxml.jackson.module.scala.DefaultScalaModule$;

import io.dropwizard.client.JerseyClientBuilder;
import javax.ws.rs.client.Client;

public class IdleRPGApplication extends Application<IdleRPGConfiguration> {
    public static void main(String[] args) throws Exception {
        new IdleRPGApplication().run(args);
    }

    @Override
    public String getName() {
        return "idlerpg-bot";
    }

    @Override
    public void initialize(Bootstrap<IdleRPGConfiguration> bootstrap) {
    }

    @Override
    public void run(IdleRPGConfiguration configuration, Environment environment) {
        environment.getObjectMapper().registerModule(DefaultScalaModule$.MODULE$);

        final Client client = new JerseyClientBuilder(environment)
            .using(configuration.getJerseyClientConfiguration())
            .build(getName());

        final HelloScalaResource scalaResource = new HelloScalaResource();
        environment.jersey().register(scalaResource);

        final String token = configuration.getToken();
        final TelegramAPICheck telegramAPICheck = new TelegramAPICheck(client, token);
        environment.healthChecks().register(telegramAPICheck.name(), telegramAPICheck);
    }

}
