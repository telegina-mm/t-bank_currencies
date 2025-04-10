/*package ru.mm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mm.service.CurrencyService;
import ru.mm.entity.Currency;

import java.util.List;


@RequestMapping("/api/currencies")
@RequiredArgsConstructor
@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping()
    public ResponseEntity<List<Currency>> getCurrencies(){
        return ResponseEntity.ok(currencyService.getCurrencies());
    }

    @PostMapping()
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        if (currency.getName() == null || currency.getBaseCurrency() == null || currency.getPriceChangeRange() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(currency);
        }
        currencyService.addCurrency(currency);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(currency);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String id){
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String id, @RequestBody Currency currency){
        return ResponseEntity.ok(currencyService.updateCurrency(id, currency));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Currency> deleteCurrency(@PathVariable String id){
        currencyService.deleteCurrencyById(id);
        return ResponseEntity.noContent().build();
    }
}
*/

package ru.mm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import ru.mm.service.CurrencyService;
import ru.mm.entity.Currency;

import java.util.List;

@RequestMapping("/api/currencies")
@RequiredArgsConstructor
@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    // Получение всех валют
    @GetMapping
    public ResponseEntity<List<Currency>> getCurrencies() {
        List<Currency> currencies = currencyService.getCurrencies();
        return ResponseEntity.ok(currencies);
    }

    // Добавление новой валюты
    @PostMapping
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        if (currency.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Currency savedCurrency = currencyService.addCurrency(currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurrency);
    }

    // Получение валюты по ID
    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String id) {
        Currency currency = currencyService.getCurrencyById(id);
        if (currency != null) {
            return ResponseEntity.ok(currency);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Обновление валюты по ID
    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String id, @RequestBody Currency currencyDetails) {
        Currency updatedCurrency = currencyService.updateCurrency(id, currencyDetails);
        if (updatedCurrency != null) {
            return ResponseEntity.ok(updatedCurrency);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Удаление валюты по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String id) {
        boolean isDeleted = currencyService.deleteCurrencyById(id);
        if (currencyService.deleteCurrencyById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}