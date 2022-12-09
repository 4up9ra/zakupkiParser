package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class HREF {

    public static String getHREF(Element element) {
        return "https://zakupki.gov.ru" +
                element.getElementsByAttributeValue("target", "_blank").get(4).attr("href");
    }
}
