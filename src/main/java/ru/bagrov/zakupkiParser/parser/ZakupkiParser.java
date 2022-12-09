package ru.bagrov.zakupkiParser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.bagrov.zakupkiParser.elements.*;

import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class ZakupkiParser {

    private final String dateRegex = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)";

    public ZakupkiParser()  {
        int pageNumber = 1;
        boolean pagesAvailable = true;
        int counter = 1;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите дату размещения закупки в формате dd.mm.yyyy: ");
        String dateFrom = scanner.nextLine();

        while (!Pattern.matches(dateRegex, dateFrom)) {
            System.out.print("Введите дату размещения закупки в формате dd.mm.yyyy: ");
            dateFrom = scanner.nextLine();
        }

        System.out.print("Введите ключевое слово для поиска: ");
        String searchWord = scanner.nextLine();

        try {
            while (pagesAvailable) {
                String url = "https://zakupki.gov.ru/epz/order/extendedsearch/results.html?searchString=" + searchWord + "&morphology=on&search-filter=%D0%94%D0%B0%D1%82%D0%B5+%D1%80%D0%B0%D0%B7%D0%BC%D0%B5%D1%89%D0%B5%D0%BD%D0%B8%D1%8F&pageNumber=" + pageNumber + "&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&sortBy=PUBLISH_DATE&fz223=on&af=on&currencyIdGeneral=-1&publishDateFrom=" + dateFrom;
                Document document = Jsoup.connect(url).get();
                Elements outerElements = document.getElementsByAttributeValue("class", "search-registry-entrys-block");
                for (Element outerElement : outerElements) {
                    if (outerElement.getElementsByAttributeValue("class", "row no-gutters registry-entry__form mr-0").isEmpty()) {
                        pagesAvailable = false;
                    }
                    Elements elements = outerElement.getElementsByAttributeValue("class", "row no-gutters registry-entry__form mr-0");
                    for (Element element : elements) {
                        System.out.println(counter++);
                        Purchase purchase = new Purchase(Type.getType(element),
                                PurchaseNumber.getPurchaseNumber(element), PurchaseName.getPurchaseName(element),
                                HREF.getHREF(element), Customer.getCustomer(element), Price.getPrice(element),
                                StartDate.getStartDate(element), CloseDate.getCloseDate(element)
                                );
                        System.out.println(purchase);
                    }
                    pageNumber++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
