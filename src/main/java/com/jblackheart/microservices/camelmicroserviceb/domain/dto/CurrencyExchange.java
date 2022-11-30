package com.jblackheart.microservices.camelmicroserviceb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {
    private Double id;
    private String from;
    private String to;
    private Double conversionMultiple;
    private Double convertedCurrency;
}
