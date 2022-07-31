package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Arrays;
import java.util.UUID;


public class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private static final DOMImplementationRegistry registry;
    private static final DOMImplementationLS impl;

    static {
        DOMImplementationRegistry instance = null;
        try {
            instance = DOMImplementationRegistry.newInstance();
        } catch (Exception e) {
            System.out.println("Error initializing DOMImplementationRegistry" + e.getMessage());
        }
        if (instance != null) {
            registry = instance;
            impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
        } else {
            registry = null;
            impl = null;
        }
    }

    public static String generateRqUid() {
        return UUID.randomUUID().toString();
        //return (UUID.randomUUID().toString()) + Arrays.toString(Thread.currentThread().getStackTrace());
    }

    public static String getRandomStringOfLength(int stringLength) {
        return RandomStringUtils.random(stringLength, 0, 128, true, true,
                ("0123456789" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "abcdefghijklmnopqrstuvwxyz" +
                        "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                        "абвгдеёжзийклмнопрстуфхцчшщъыьэюя").toCharArray());
    }

    public static String getRandomStringOfNumbersOfLength(int stringLength) {
        return RandomStringUtils.randomNumeric(stringLength);
    }

    public static String getRandomStringOfNumbersExcept1ofLength(int stringLength) {
        return RandomStringUtils.random(stringLength, 0, 9, false, true,
                ("023456789").toCharArray());
    }

    public static String formatXmlString(String message) {
        try {
            final InputSource src = new InputSource(new StringReader(message));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            return xmlDocumentToFormattedString(document);
        } catch (Exception e) {
            LOGGER.error("Failed to format message: {}. Returned original value", message, e);
            return message;
        }
    }

    public static String xmlDocumentToFormattedString(Node document) {
        final LSSerializer writer = impl.createLSSerializer();
        // Set this to true if the output needs to be beautified.
        writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
        return writer.writeToString(document);
    }
}
