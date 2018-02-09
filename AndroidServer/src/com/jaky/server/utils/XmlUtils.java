package com.jaky.server.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class XmlUtils {

    private static String filename = "users.xml";

    public static Document getDocument() throws DocumentException {
        URL url = XmlUtils.class.getClassLoader().getResource(filename);
        String realPath = url.getPath();
        System.out.println(realPath);

        SAXReader reader = new SAXReader();
        Document document = reader.read(realPath);
        return document;
    }

    public static void write2Xml(Document document) throws IOException {
        URL url = XmlUtils.class.getClassLoader().getResource(filename);
        String realPath = url.getPath();

        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileOutputStream(realPath),format);
        writer.write(document);
        writer.close();
    }

}
