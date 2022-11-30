package com.jblackheart.microservices.camelmicroserviceb.processor;

import com.jblackheart.microservices.camelmicroserviceb.domain.dto.CurrencyExchange;
import com.jblackheart.microservices.camelmicroserviceb.util.ExchangeUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeProcessor implements Processor {
    @Autowired
    private ExchangeUtil exchangeUtil;

    @Override
    public void process(Exchange exchange) throws Exception {
        CurrencyExchange data = exchange.getIn().getBody(CurrencyExchange.class);
        exchange.getIn().getBody(CurrencyExchange.class).setConvertedCurrency(exchangeUtil.calculateCurrencyConversion((data)));
    }
}
