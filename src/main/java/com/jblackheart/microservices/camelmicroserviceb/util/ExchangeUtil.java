package com.jblackheart.microservices.camelmicroserviceb.util;

import com.jblackheart.microservices.camelmicroserviceb.domain.dto.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeUtil {
    @Autowired
    private IntegrationLogger logger;

    public Double calculateCurrencyConversion(CurrencyExchange currencyExchange) {
        double result = 0;
        try {
            result = currencyExchange.getId() * currencyExchange.getConversionMultiple();
        } catch (Exception ex) {
            logger.error("Something went wrong while calculating currency ", ex, this.getClass().getName());
        }
        return result;
    }
}
