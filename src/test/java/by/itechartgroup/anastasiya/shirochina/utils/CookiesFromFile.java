package by.itechartgroup.anastasiya.shirochina.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CookiesFromFile {
    public static String getCookiesByNameFromFile(File file, String key) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);
        JsonNode cookiesNode = rootNode.get("cookies");
        if (cookiesNode != null && cookiesNode.isArray()) {
            for (JsonNode cookieNode : cookiesNode) {
                String name = cookieNode.get("name").asText();
                if (name.equals(key)) {
                    key = cookieNode.get("value").asText();
                    return key;
                }
            }
        } return null;
    }
}
