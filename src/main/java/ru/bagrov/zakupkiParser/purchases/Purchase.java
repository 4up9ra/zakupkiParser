package ru.bagrov.zakupkiParser.purchases;

public class Purchase {

    private String type;
    private String purchaseNumber;
    private String purchaseName;
    private String link;
    private String customer;
    private String price;
    private String startDate;
    private String closeDate;

    public Purchase(String type, String purchaseNumber, String purchaseName, String link,
                    String customer, String price, String startDate, String closeDate) {
        this.type = type;
        this.purchaseNumber = purchaseNumber;
        this.purchaseName = purchaseName;
        this.link = link;
        this.customer = customer;
        this.price = price;
        this.startDate = startDate;
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return type + "\n" +
                "№ " + purchaseNumber +
                " " + purchaseName + "\n" +
                link + "\n" + customer + "\n" +
                "НМЦ: " + price + " ₽" + "\n" +
                "Дата размещения: " + startDate + "\n" +
                "Дата закрытия: " + closeDate + "\n" +
                "-----------------------------------------------------------------------"
                ;
    }
}
