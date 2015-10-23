package org.soluvas.starter.rabbitmq;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.LoggingErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.joda.time.DateTime;
import org.lskk.lumen.core.*;
import org.lskk.lumen.core.util.AsError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soluvas.starter.rabbitmq.core.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Optional;

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

    @Override
    public void configure() throws Exception {
        onException(Exception.class).bean(asError).bean(toJson).handled(true);
        errorHandler(new LoggingErrorHandlerBuilder(log));
        final String topic = "starter.in";
        from("rabbitmq://localhost/amq.topic?connectionFactory=#amqpConnFactory&exchangeType=topic&autoDelete=false&routingKey=" + topic)
                .to("log:IN." + topic + "?showHeaders=true&showAll=true&multiline=true")
                .process(exchange -> {
                    final StarterThing thing = toJson.getMapper().readValue(
                            exchange.getIn().getBody(byte[].class), StarterThing.class);
                    if (thing instanceof CommunicateAction) {
                        final CommunicateAction communicateAction = (CommunicateAction) thing;

                        // reply
                        exchange.getIn().setBody(new Status());
                    } else {
                        // unknown thing, ignore
                        exchange.getOut().setBody(null);
                    }
                })
                .bean(toJson);
                //.to("log:OUT" + topic);
    }
}
