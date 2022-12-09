package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class StartDate {

    public static String getStartDate(Element element) {
        return element.getElementsByAttributeValue("class", "data-block__value").get(0).text();
    }
}
