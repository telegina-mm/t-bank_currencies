/* package ru.mm.service;

import org.springframework.stereotype.Service;
import ru.mm.entity.Currency;

import java.util.ArrayList;
import java.util.List;
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

    public boolean deleteCurrencyById(String id){
        currencies.remove(getCurrencyById(id));
        return true;
    }
}
*/

package ru.mm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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