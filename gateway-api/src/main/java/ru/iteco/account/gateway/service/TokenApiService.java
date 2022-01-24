package ru.iteco.account.gateway.service;

public interface TokenApiService {

    String getToken(String clientId, String clientSecret, String audience);

}
