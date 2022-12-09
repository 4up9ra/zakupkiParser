package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementCloseDate {

    public static String getElementCloseDate(Element element) {
        return element.getElementsByAttributeValue("class", "data-block__value").get(2).text();
    }
}
