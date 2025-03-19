package ru.mm.model;

public class Currency {
    private String id;
    private String name;
    private String baseCurrency = "RUB";
    private String priceChangeRange;
    private String description;


    public Currency(String id, String name, String priceChangeRange, String description) {
        this.id = id;
        this.name = name;
        this.priceChangeRange = priceChangeRange;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getPriceChangeRange() {
        return priceChangeRange;
    }

    public void setPriceChangeRange(String priceChangeRange) {
        this.priceChangeRange = priceChangeRange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}