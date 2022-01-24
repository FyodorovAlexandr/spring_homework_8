package ru.iteco.account.model.exceptions;

public class BankBookNotFoundException extends RuntimeException {
    public BankBookNotFoundException(String message) {
        super(message);
    }
}