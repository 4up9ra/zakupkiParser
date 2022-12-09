package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementPrice {

    public static String getElementPrice(Element element) {
        Double price = Double.parseDouble(element.getElementsByAttributeValue("class", "price-block__value").text()
                .replaceAll(" ", "").replaceAll(",", ".").replaceAll("₽", ""));
        return price.toString();
    }

}
