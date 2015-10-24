package org.soluvas.tutorial.rabbitmq;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.LoggingErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soluvas.tutorial.rabbitmq.core.AsError;
import org.soluvas.tutorial.rabbitmq.core.StarterThing;
import org.soluvas.tutorial.rabbitmq.core.Status;
import org.soluvas.tutorial.rabbitmq.core.ToJson;
import org.soluvas.tutorial.rabbitmq.jpa.Place;
import org.soluvas.tutorial.rabbitmq.jpa.PlaceRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Profile("daemonApp")
public class StarterRouter extends RouteBuilder {

    private static final Logger log = LoggerFactory.getLogger(StarterRouter.class);

    @Inject
    private ToJson toJson;
    @Inject
    private AsError asError;
    @Inject
    private ProducerTemplate producer;
    @Inject
    private PlaceRepository placeRepo;

    @Override
    public void configure() throws Exception {
        onException(Exception.class).bean(asError).bean(toJson).handled(true);
        errorHandler(new LoggingErrorHandlerBuilder(log));
        final String topic = "starter.place";
        from("rabbitmq://localhost/amq.topic?connectionFactory=#amqpConnFactory&exchangeType=topic&autoDelete=false&routingKey=" + topic)
                .to("log:IN." + topic + "?showHeaders=true&showAll=true&multiline=true")
                .process(exchange -> {
                    final StarterThing thing = toJson.getMapper().readValue(
                            exchange.getIn().getBody(byte[].class), StarterThing.class);
                    if (thing instanceof Place) {
                        Place place = (Place) thing;
                        log.info("Saving {} ...", place);
                        place = placeRepo.save(place);
                        // reply
                        exchange.getIn().setBody(new Status(place));
                    } else {
                        // unknown thing, ignore
                        exchange.getOut().setBody(null);
                    }
                })
                .bean(toJson);
                //.to("log:OUT" + topic);
    }
}
