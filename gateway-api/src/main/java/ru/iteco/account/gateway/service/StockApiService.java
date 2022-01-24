package ru.iteco.account.gateway.service;

import java.util.List;

public interface StockApiService {

    String getStockQuotes(List<String> tickets);

}
