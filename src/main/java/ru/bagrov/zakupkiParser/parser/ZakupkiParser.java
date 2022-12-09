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
                        //Тип закупки
//                        System.out.println(element.getElementsByAttributeValue("class", "col-9 p-0 registry-entry__header-top__title text-truncate").text());
                        System.out.println(ElementType.getElementType(element));
                        //Номер закупки
//                        System.out.print(element.getElementsByAttributeValue("class", "registry-entry__header-mid__number").text() + " ");
                        System.out.print(ElementPurchaseNumber.getElementPurchaseNumber(element) + " ");
                        //Название закупки
//                        System.out.println(element.getElementsByAttributeValue("class", "registry-entry__body-value").text());
                        System.out.println(ElementPurchaseName.getElementPurchaseName(element));
                        //Ссылка на документацию
//                        System.out.println(element.getElementsByAttributeValue("target", "_blank").attr("href"));
                        System.out.println(ElementHREF.getElementHREF(element));
                        //Заказчик
//                        System.out.println(element.getElementsByAttributeValue("target", "_blank").get(2).text());
                        System.out.println(ElementCustomer.getElementCustomer(element));
                        //НМЦ
//                        System.out.println("НМЦ: " + element.getElementsByAttributeValue("class", "price-block__value").text());
                        System.out.println("НМЦ: " + ElementPrice.getElementPrice(element) + " ₽");
                        //дата размещения
//                        System.out.println("Дата размещения: " + element.getElementsByAttributeValue("class", "data-block__value").get(0).text());
                        System.out.println("Дата размещения: " + ElementStartDate.getElementStartDate(element));
                        //дата закрытия
//                        System.out.println("Дата закрытия: " + element.getElementsByAttributeValue("class", "data-block__value").get(2).text());
                        System.out.println("Дата закрытия: " + ElementCloseDate.getElementCloseDate(element));

                        System.out.println("-----------------------------------------------------------------------------------------");
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
