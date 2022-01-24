package ru.iteco.account.model.exceptions;

public class BankBookException extends RuntimeException{
    public BankBookException(String message) {
        super(message);
    }
}
