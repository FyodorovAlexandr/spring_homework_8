package ru.iteco.account.service;

import org.springframework.http.ResponseEntity;
import ru.iteco.account.model.dto.BankBookDto;

import java.util.List;

public interface BankBookService {
    List<BankBookDto> findByUserId(Integer userId);
    BankBookDto findById(Integer bankBookId);
    BankBookDto createBankBook(BankBookDto bankBookDto);
    BankBookDto updateBankBook(BankBookDto bankBookDto);
    ResponseEntity<String> deleteBankBookById(Integer bankBookId);
    ResponseEntity<String> deleteBankBookByUserId(Integer userId);
}
