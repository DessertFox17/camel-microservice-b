package com.jblackheart.microservices.camelmicroserviceb.processor;

import com.jblackheart.microservices.camelmicroserviceb.domain.dto.CurrencyExchange;
import com.jblackheart.microservices.camelmicroserviceb.util.IntegrationLogger;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeProcessor implements Processor {

    @Autowired
    private IntegrationLogger logger;

    @Override
    public void process(Exchange exchange) throws Exception {
        CurrencyExchange data = exchange.getIn().getBody(CurrencyExchange.class);
        exchange.getIn().getBody(CurrencyExchange.class).setConvertedCurrency(calculateCurrencyConversion((data)));
    }

    private Double calculateCurrencyConversion(CurrencyExchange currencyExchange) {
        double result = 0;
        try {
            result = currencyExchange.getId() * currencyExchange.getConversionMultiple();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex, this.getClass());
        }
        return result;
    }
}
