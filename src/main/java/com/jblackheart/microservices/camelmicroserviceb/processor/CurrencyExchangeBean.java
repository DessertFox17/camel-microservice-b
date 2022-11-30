package com.jblackheart.microservices.camelmicroserviceb.processor;

import com.jblackheart.microservices.camelmicroserviceb.domain.dto.CurrencyExchange;
import com.jblackheart.microservices.camelmicroserviceb.util.ExchangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeBean {
    @Autowired
    private ExchangeUtil exchangeUtil;

    public void calculateCurrencyExchange(CurrencyExchange currencyExchange){
        currencyExchange.setConvertedCurrency(exchangeUtil.calculateCurrencyConversion((currencyExchange)));
    }
}
