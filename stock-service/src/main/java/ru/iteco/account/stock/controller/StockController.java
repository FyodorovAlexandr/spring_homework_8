package ru.iteco.account.stock.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.account.stock.model.HistoricalQuotesRequest;
import ru.iteco.account.stock.model.HistoricalQuotesResponse;
import ru.iteco.account.stock.model.StockQuotes;
import ru.iteco.account.stock.service.StockServiceApi;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockServiceApi stockServiceApi;

    @PostMapping("/get-stock-quotes")
    public StockQuotes getStockQuotes(@RequestBody List<String> tickets) {
        return stockServiceApi.getStockQuotesByTicket(tickets);
    }

    @PostMapping("/get-historical-quotes")
    public HistoricalQuotesResponse getStockQuotes(@RequestBody HistoricalQuotesRequest request) {
        return stockServiceApi.getHistoricalQuotes(request);
    }

}
