package ru.mm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mm.entity.Currency;
import ru.mm.repository.CurrencyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency getCurrencyById(String id) {
        return currencyRepository.findById(id).orElse(null);
    }

    public Currency updateCurrency(String id, Currency currencyDetails) {
        return currencyRepository.findById(id).map(currency -> {
            currency.setName(currencyDetails.getName());
            currency.setPriceChangeRange(currencyDetails.getPriceChangeRange());
            return currencyRepository.save(currency);
        }).orElse(null);
    }

    public boolean deleteCurrencyById(String id) {
        if (currencyRepository.existsById(id)) {
            currencyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}