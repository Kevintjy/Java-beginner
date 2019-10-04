package main.java.ATM;

public enum Currency {
    USD("USD"),
    PLN("PLN"),
    JPY("JPY"),
    EUR("EUR"),
    GBP("GBP"),
    RUB("RUB"),
    CAD("CAD");


    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}