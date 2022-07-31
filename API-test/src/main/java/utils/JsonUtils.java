package utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.nio.charset.StandardCharsets;

public class JsonUtils {

    public static DocumentContext getDocumentContextFromString(String string) {
        return JsonPath.parse(string);
    }

    public static DocumentContext getDocumentContextFromByteArray(byte[] byteArray) {
        return JsonPath.parse(new String(byteArray, StandardCharsets.UTF_8));
    }

    public static DocumentContext getDocumentContextWithoutNode(DocumentContext documentContext, String jsonpathExpression) {
        return documentContext.delete(jsonpathExpression);
    }

    public static Object getNodeValue(DocumentContext jsonContext, String jsonPath) {
        return jsonContext.read(jsonPath);
    }

    public static <T> DocumentContext getDocumentContextWithUpdatedNodeValue(DocumentContext documentContext, String jsonpathExpression, T newValue) {
        return documentContext.map(jsonpathExpression, (currentValue, configuration) -> newValue);
    }

    public static String getStringFromDocumentContext(DocumentContext documentContext) {
        return documentContext.jsonString();
    }
}
