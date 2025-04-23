package ru.mm.job;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mm.entity.Currency;
import ru.mm.repository.CurrencyRepository;

import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyRateJob {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CurrencyRepository currencyRepository;

    private static final String CBR_API_URL = "https://www.cbr-xml-daily.ru/daily_json.js";

    @Scheduled(cron = "0 0 * * * ?") // Запуск каждый час
    public void monitorCurrencyRates() {
        try {
            String response = restTemplate.getForObject(CBR_API_URL, String.class);

            JsonNode root = objectMapper.readTree(response);
            JsonNode valutes = root.get("Valute");

            List<Currency> rules = currencyRepository.findAll();

            rules.forEach(rule -> {
                JsonNode currency = valutes.get(rule.getBaseCurrency());
                if (currency != null) {
                    double currentValue = currency.get("Value").asDouble();
                    double previousValue = currency.get("Previous").asDouble();

                    double percentageChange = ((currentValue - previousValue) / previousValue) * 100;

                    if (rule.getPriceChangeRange() < 0) {
                        if (percentageChange <= rule.getPriceChangeRange()) {
                            System.out.println(rule.getDescription());
                        }
                    } else {
                        if (percentageChange >= rule.getPriceChangeRange()) {
                            System.out.println(rule.getDescription());
                        }
                    }
                }
            });

        } catch (Exception e) {
            System.err.println("Ошибка при мониторинге курсов валют: " + e.getMessage());
        }
    }
}

