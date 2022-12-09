package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class CloseDate {

    public static String getCloseDate(Element element) {
        return element.getElementsByAttributeValue("class", "data-block__value").get(2).text();
    }
}
