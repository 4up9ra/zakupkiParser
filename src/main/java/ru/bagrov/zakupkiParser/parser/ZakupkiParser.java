package ru.bagrov.zakupkiParser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bagrov.zakupkiParser.elements.*;
import ru.bagrov.zakupkiParser.purchases.*;

import java.util.*;

@Component
public class ZakupkiParser {

    private static final String BLOCK_ELEMENT = "search-registry-entrys-block";
    private static final String PURCHASE_ELEMENT = "row no-gutters registry-entry__form mr-0";

    public ZakupkiParser() {
        doParse();
    }

    @Scheduled(fixedDelay = 20000)
    public static void doParse()    {

        /*
        ***** This is for custom search *****
         ---------------------------------
        final String dateRegex = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите дату размещения закупки в формате dd.mm.yyyy: ");
        String dateFrom = scanner.nextLine();

        while (!Pattern.matches(dateRegex, dateFrom)) {
            System.out.print("Введите дату размещения закупки в формате dd.mm.yyyy: ");
            dateFrom = scanner.nextLine();
        }
        System.out.print("Введите ключевое слово или слова через запятую для поиска: ");
        List<String> searchWords = Arrays.asList(scanner.nextLine().split(","));
        scanner.close();
         ---------------------------------
         */

//        String dateFrom = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String dateFrom = "09.12.2022";

        String words = "кип, давления";
        List<String> searchWords = Arrays.asList(words.split(","));
//        String searchWord = scanner.nextLine();

        try {
            for (String searchWord : searchWords) {
                int pageNumber = 1;
                boolean pagesAvailable = true;
                while (pagesAvailable) {
                    String url = "https://zakupki.gov.ru/epz/order/extendedsearch/results.html?searchString=" + searchWord + "&morphology=on&search-filter=%D0%94%D0%B0%D1%82%D0%B5+%D1%80%D0%B0%D0%B7%D0%BC%D0%B5%D1%89%D0%B5%D0%BD%D0%B8%D1%8F&pageNumber=" + pageNumber + "&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&sortBy=PUBLISH_DATE&fz223=on&af=on&currencyIdGeneral=-1&publishDateFrom=" + dateFrom;
                    Document document = Jsoup.connect(url).get();
                    Elements outerElements = document.getElementsByAttributeValue("class", BLOCK_ELEMENT);
                    for (Element outerElement : outerElements) {
                        if (outerElement.getElementsByAttributeValue("class", PURCHASE_ELEMENT).isEmpty()) {
                            pagesAvailable = false;
                        }
                        Elements elements = outerElement.getElementsByAttributeValue("class", PURCHASE_ELEMENT);
                        for (Element element : elements) {
                            Purchase purchase = new Purchase(ZakupkiElements.getType(element),
                                    ZakupkiElements.getPurchaseNumber(element), ZakupkiElements.getPurchaseName(element),
                                    ZakupkiElements.getLink(element), ZakupkiElements.getCustomer(element), ZakupkiElements.getPrice(element),
                                    ZakupkiElements.getStartDate(element), ZakupkiElements.getCloseDate(element)
                            );
                            PurchasesList.mapOfPurchases.put(ZakupkiElements.getPurchaseNumber(element), purchase);
                        }
                        pageNumber++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        printPurchases();
    }

    public static void printPurchases()    {
        int counter = 1;
        for (Map.Entry<String, Purchase> entry : PurchasesList.mapOfPurchases.entrySet())  {
            System.out.println(counter++ + ": " + entry.getValue());
        }
    }
}
