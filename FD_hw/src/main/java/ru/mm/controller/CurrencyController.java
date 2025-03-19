package ru.mm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mm.service.CurrencyService;
import ru.mm.model.Currency;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getCurrencies(){
        return ResponseEntity.ok(currencyService.getCurrencies());
    }

    @PostMapping("/currencies")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        if (currency.getName() == null || currency.getBaseCurrency() == null || currency.getPriceChangeRange() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(currency);
        }
        currencyService.addCurrency(currency);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(currency);
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String id){
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @PutMapping("/currencies/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String id, @RequestBody Currency currency){
        return ResponseEntity.ok(currencyService.updateCurrency(id, currency));
    }

    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<Currency> deleteCurrency(@PathVariable String id){
        currencyService.deleteCurrencyById(id);
        return ResponseEntity.noContent().build();
    }
}
