package com.jblackheart.microservices.camelmicroserviceb.route;

import com.jblackheart.microservices.camelmicroserviceb.domain.dto.CurrencyExchange;
import com.jblackheart.microservices.camelmicroserviceb.processor.CurrencyExchangeBean;
import com.jblackheart.microservices.camelmicroserviceb.processor.CurrencyExchangeProcessor;
import com.jblackheart.microservices.camelmicroserviceb.processor.CurrencyExchangeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {
    @Autowired
    private CurrencyExchangeProcessor currencyProcessor;
    @Autowired
    private CurrencyExchangeTransformer currencyTransformer;
    @Autowired
    private CurrencyExchangeBean currencyExchangeBean;

    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .process(currencyProcessor)
                .bean(currencyTransformer)
                .bean(currencyExchangeBean)
                .to("log:received-message-from-active-mq");
    }
}
