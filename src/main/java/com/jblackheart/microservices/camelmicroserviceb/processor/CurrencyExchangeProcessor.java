package com.jblackheart.microservices.camelmicroserviceb.processor;

import com.jblackheart.microservices.camelmicroserviceb.domain.dto.CurrencyExchange;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class CurrencyExchangeProcessor implements Processor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void process(Exchange exchange) throws Exception {
        CurrencyExchange data = exchange.getIn().getBody(CurrencyExchange.class);
        exchange.getIn().getBody(CurrencyExchange.class).setConvertedCurrency(calculateCurrencyConversion((data)));
    }

    private Double calculateCurrencyConversion(CurrencyExchange currencyExchange) {
        return currencyExchange.getId() * currencyExchange.getConversionMultiple();
    }
}
