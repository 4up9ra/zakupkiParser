package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class Type {

    public static String getType(Element element) {
        return element.getElementsByAttributeValue("class", "col-9 p-0 registry-entry__header-top__title text-truncate").text();
    }
}
