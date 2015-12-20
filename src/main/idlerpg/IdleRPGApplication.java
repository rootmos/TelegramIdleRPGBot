package idlerpg;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import idlerpg.resources.HelloWorldResource;
import idlerpg.resources.HelloScalaResource;
import idlerpg.health.TemplateHealthCheck;

import com.fasterxml.jackson.module.scala.DefaultScalaModule$;

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
        environment.getObjectMapper().registerModule(DefaultScalaModule$.MODULE$);

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName());
        environment.jersey().register(resource);

        final HelloScalaResource scalaResource = new HelloScalaResource();
        environment.jersey().register(scalaResource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
