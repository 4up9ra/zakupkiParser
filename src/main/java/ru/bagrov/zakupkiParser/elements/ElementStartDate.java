package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementStartDate {

    public static String getElementStartDate(Element element) {
        return element.getElementsByAttributeValue("class", "data-block__value").get(0).text();
    }
}
