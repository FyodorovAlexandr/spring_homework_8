package ru.iteco.account.currency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.iteco.account.currency.model.AllCurrencyExchange;
import ru.iteco.account.currency.model.ConvertResult;
import ru.iteco.account.currency.model.ConverterRequest;
import ru.iteco.account.currency.service.ExchangeApi;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final ExchangeApi exchangeApi;

    @PostMapping("/convert")
    public ConvertResult convertAmount(@RequestBody ConverterRequest converterRequest,
                                       @RequestHeader Map<String, String> headers) {
        log.info("Request with headers: {}", headers);
        return exchangeApi.convert(converterRequest);
    }

    @GetMapping("/all-exchange")
    public AllCurrencyExchange getAllExchange(@RequestHeader Map<String, String> headers) {
        log.info("Request with headers: {}", headers);
        return exchangeApi.getAllCurrencyExchange();
    }

}
