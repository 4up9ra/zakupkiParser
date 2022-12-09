package ru.bagrov.zakupkiParser.parser;

public class Purchase {

    private String elementType;
    private String elementPurchaseNumber;
    private String elementPurchaseName;
    private String elementHREF;
    private String elementCustomer;
    private String elementPrice;
    private String elementStartDate;
    private String elementCloseDate;

    public Purchase(String elementType, String elementPurchaseNumber
            , String elementPurchaseName, String elementHREF, String elementCustomer
            , String elementPrice, String elementStartDate, String elementCloseDate) {
        this.elementType = elementType;
        this.elementPurchaseNumber = elementPurchaseNumber;
        this.elementPurchaseName = elementPurchaseName;
        this.elementHREF = elementHREF;
        this.elementCustomer = elementCustomer;
        this.elementPrice = elementPrice;
        this.elementStartDate = elementStartDate;
        this.elementCloseDate = elementCloseDate;
    }

    @Override
    public String toString() {
        return elementType + "\n" +
                elementPurchaseNumber +
                " " + elementPurchaseName + "\n" +
                elementHREF + "\n" + elementCustomer + "\n" +
                "НМЦ: " + elementPrice + " ₽" + "\n" +
                "Дата размещения: " + elementStartDate + "\n" +
                "Дата закрытия: " + elementCloseDate + "\n" +
                "-----------------------------------------------------------------------"
                ;
    }
}
