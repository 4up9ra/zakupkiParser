package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class Price {

    public static String getPrice(Element element) {
        Double price = Double.parseDouble(element.getElementsByAttributeValue("class", "price-block__value").text()
                .replaceAll(" ", "").replaceAll(",", ".").replaceAll("₽", ""));
        return price.toString();
    }
}
