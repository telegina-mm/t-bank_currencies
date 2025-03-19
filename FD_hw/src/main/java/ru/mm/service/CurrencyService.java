package ru.mm.service;

import org.springframework.stereotype.Service;
import ru.mm.model.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CurrencyService {
    private final List<Currency> currencies = new ArrayList<>();

    public List<Currency> getCurrencies(){
        return currencies;
    }

    public Currency addCurrency(Currency currency){
        for(Currency cur : currencies){
            if(cur.getId().equals(currency.getId())){
                return currency;
            }
        }
        String id = UUID.randomUUID().toString();
        currency.setId(id);
        currencies.add(currency);
        return currency;
    }

    public Currency getCurrencyById(String id){
        return currencies.stream()
                .filter(currency -> currency.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(("Валюта не найдена с ID:" + id)));
    }

    public Currency updateCurrency(String id, Currency currency){
        Currency updatedCurrency = getCurrencyById(id);
        updatedCurrency.setName(currency.getName());
        updatedCurrency.setBaseCurrency(currency.getBaseCurrency());
        updatedCurrency.setPriceChangeRange(currency.getPriceChangeRange());
        updatedCurrency.setDescription(currency.getDescription());
        return updatedCurrency;
    }

    public void deleteCurrencyById(String id){
        currencies.remove(getCurrencyById(id));
    }
}
