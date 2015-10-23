package org.soluvas.starter.rabbitmq.core;

import com.google.common.base.Throwables;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.lskk.lumen.core.Error;
import org.springframework.stereotype.Service;

/**
 * Created by ceefour on 10/15/15.
 */
@Service
public class AsError {

    public org.lskk.lumen.core.Error process(@ExchangeProperty(Exchange.EXCEPTION_CAUGHT) Exception e) {
        return new org.soluvas.starter.rabbitmq.core.Error(e.toString(), Throwables.getStackTraceAsString(e));
    }

}
