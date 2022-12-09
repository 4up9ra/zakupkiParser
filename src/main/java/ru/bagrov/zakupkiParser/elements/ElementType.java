package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementType{

    public static String getElementType(Element element) {
        return element.getElementsByAttributeValue("class", "col-9 p-0 registry-entry__header-top__title text-truncate").text();
    }
}
