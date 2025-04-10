package ru.mm.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Setter
@Getter
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String baseCurrency = "RUB";

    @Column(nullable = false)
    private String priceChangeRange;

    @Column(nullable = false)
    private String description;


    public Currency(String id, String name, String priceChangeRange, String description) {
        this.id = id;
        this.name = name;
        this.priceChangeRange = priceChangeRange;
        this.description = description;
    }

}