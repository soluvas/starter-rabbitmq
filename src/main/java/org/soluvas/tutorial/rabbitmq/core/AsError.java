package org.soluvas.tutorial.rabbitmq.core;

import com.google.common.base.Throwables;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.springframework.stereotype.Service;

import java.lang.*;

/**
 * Created by ceefour on 10/15/15.
 */
@Service
public class AsError {

    public Error process(@ExchangeProperty(Exchange.EXCEPTION_CAUGHT) Exception e) {
        return new Error(e.toString(), Throwables.getStackTraceAsString(e));
    }

}
