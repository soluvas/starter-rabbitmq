package org.soluvas.starter.rabbitmq.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * Created by ceefour on 20/04/2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property="@type")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name="Status", value=Status.class),
        @JsonSubTypes.Type(name="Error", value=Error.class),
        @JsonSubTypes.Type(name="CommunicateAction", value=CommunicateAction.class),
})
public interface StarterThing extends Serializable {
}
