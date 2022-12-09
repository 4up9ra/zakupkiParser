package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementHREF {

    public static String getElementHREF(Element element) {
        return "https://zakupki.gov.ru" +
                element.getElementsByAttributeValue("target", "_blank").get(4).attr("href");
    }
}
