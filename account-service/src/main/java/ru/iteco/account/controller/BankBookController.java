package ru.iteco.account.controller;

import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.service.BankBookService;
import ru.iteco.account.validation.Created;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService){
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<List<BankBookDto>> getBankBookByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(bankBookService.findByUserId(userId));
    }

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getBankBookById(@PathVariable Integer bankBookId) {
        return ResponseEntity.ok(bankBookService.findById(bankBookId));
    }

    @Validated(Created.class)
    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@Valid @RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.ok(bankBookService.createBankBook(bankBookDto));
    }

    @Validated(Update.class)
    @PutMapping
    public ResponseEntity<BankBookDto> updateBankBook(@Valid @RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.ok(bankBookService.updateBankBook(bankBookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankBook(@PathVariable Integer id) {
        return bankBookService.deleteBankBookById(id);
    }

    @DeleteMapping("/by-user-id/{userId}")
    public ResponseEntity <String> deleteBankBookByUserId(@PathVariable Integer userId) {
        return bankBookService.deleteBankBookByUserId(userId);
    }
}
