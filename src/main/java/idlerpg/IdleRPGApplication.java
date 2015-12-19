package idlerpg;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import idlerpg.resources.HelloWorldResource;
import idlerpg.health.TemplateHealthCheck;

public class IdleRPGApplication extends Application<IdleRPGConfiguration> {
    public static void main(String[] args) throws Exception {
        new IdleRPGApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<IdleRPGConfiguration> bootstrap) {
    }

    @Override
    public void run(IdleRPGConfiguration configuration, Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName());
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
