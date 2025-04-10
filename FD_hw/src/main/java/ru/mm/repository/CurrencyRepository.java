package ru.mm.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mm.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
