package ru.iteco.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import ru.iteco.account.mapper.BankBookMapper;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.model.entity.BankBookEntity;
import ru.iteco.account.model.entity.CurrencyEntity;
import ru.iteco.account.model.exceptions.BankBookException;
import ru.iteco.account.model.exceptions.BankBookNotFoundException;
import ru.iteco.account.repository.BankBookRepository;
import ru.iteco.account.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Validated
public class BankBookServiceImpl implements BankBookService {

    private final BankBookRepository bankBookRepository;
    private final CurrencyRepository currencyRepository;
    private final BankBookMapper bankBookMapper;

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {
        List<BankBookDto> list = bankBookRepository.findAllByUserId(userId).stream()
                .map(bankBookMapper::mapToDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов");
        }
        return list;
    }

    @Override
    public BankBookDto findById(Integer bankBookId) {
        return bankBookRepository.findById(bankBookId)
                .map(bankBookMapper::mapToDto)
                .orElseThrow(() -> new BankBookNotFoundException("Счет не найден"));
    }

    @Override
    public  BankBookDto createBankBook(BankBookDto bankBookDto) {
        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());
        Optional<BankBookEntity> bankBookEntityOptional = bankBookRepository.findByUserIdAndNumberAndCurrency(
                bankBookDto.getUserId(),
                bankBookDto.getNumber(),
                currency);
        if (bankBookEntityOptional.isPresent()) {
            throw new BankBookException("Счет с данной валютой уже имеется");
        }
        BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(bankBookRepository.save(bankBookEntity));
    }

    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) {
        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBookDto.getId())
                .orElseThrow(() -> new BankBookNotFoundException("Счет не найден"));

        if (!bankBookEntity.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookException("Номер счета изменить нельзя");
        }

        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());
        bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(bankBookRepository.save(bankBookEntity));
    }

    @Override
    public ResponseEntity<String> deleteBankBookById(Integer bankBookId) {
        if (bankBookRepository.findById(bankBookId).isEmpty()) {
            throw new BankBookNotFoundException("Счет id " + bankBookId + " не существует");
        }
        else {
            bankBookRepository.deleteById(bankBookId);
        }
        return ResponseEntity.ok("Счет id " + bankBookId + " удален");
    }

    @Override
    public ResponseEntity<String> deleteBankBookByUserId(Integer userId) {
        List<BankBookEntity> bankBookEntities = bankBookRepository.findAllByUserId(userId);

        if (bankBookEntities.isEmpty()) {
            throw new BankBookNotFoundException("У клиента userId " + userId + " нет счетов");
        }
        else {
            bankBookRepository.deleteAllByUserId(userId);
        }
        return ResponseEntity.ok("Счета у клиента userId " + userId + " удалены");
    }
}