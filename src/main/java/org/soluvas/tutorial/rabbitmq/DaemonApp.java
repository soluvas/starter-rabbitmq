package org.soluvas.tutorial.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("daemonApp")
public class DaemonApp implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DaemonApp.class)
                .profiles("daemonApp")
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
