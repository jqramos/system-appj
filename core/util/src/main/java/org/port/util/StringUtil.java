package org.port.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class StringUtil {

    private StringUtil() {}

    public static Boolean isEmpty(String text) {
        return (text == null || text.length() < 1);
    }

    public static Boolean isEmpty(String[] strings) {
        for (String string : strings) {
            if (isEmpty(string)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static Boolean isNumber(String text) {
        return text.matches("[0-9]+");
    }

    public static Boolean isNotNumber(String text) {
        return !text.matches("[0-9]*\\.?[0-9]*");
    }

    public static List<String> toList(String text, String delim) {
        List<String> list = new ArrayList<>();

        if (isNotEmpty(text)) {
            String[] textArr = text.split(delim);
            list.addAll(Arrays.asList(textArr));
        }

        return list;
    }

    public static Map<String, Object> toMap(String text) {
        Map<String, Object> map = new HashMap<>();
        List<String> pairs = toList(text, "&");

        pairs.forEach(pair -> {
            String[] entry = pair.split("=");
            if (entry.length == 2) {
                map.put(entry[0].trim(), entry[1].trim());
            }
        });

        return map;
    }

    public static String toQueryStringNoSort(Map<String, Object> body) {
        return body.entrySet()
                .stream()
                .map(p -> {
                    Object value = Optional.ofNullable(p.getValue()).orElse("");
                    return value.toString();
                })
                .reduce((p1, p2) -> p1 + "" + p2)
                .orElse("");
    }

    /**
     * Generate a query string formatted text based from the key value pair of the input. Includes empty values
     * @param body Map that will be converted to a query string
     * @return a query string formatted text
     *
     */
    public static String toQueryString(Map<String, Object> body) {
        return body.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(p -> {
                    Object value = Optional.ofNullable(p.getValue()).orElse("");
                    return p.getKey() + "=" + value;
                })
                .reduce((p1, p2) -> p1 + "&" + p2)
                .orElse("");
    }

    /**
     * Generate a query string formatted text based from the key value pair of the input. Removes empty values
     * @param body Map that will be converted to a query string
     * @return a query string formatted text
     *
     */
    public static String toQueryStringRemoveEmpty(Map<String, Object> body) {

        StringBuilder sb = new StringBuilder();

        try {
            if (body != null && body.size() > 0) {
                Set<String> keySet = body.keySet();
                List<String> keyList = new ArrayList<>(keySet);
                Collections.sort(keyList);

                int len = keyList.size();
                for (int i = 0; i < len; i++) {
                    String key = keyList.get(i);
                    if (!isEmpty(String.valueOf(body.get(key))) && body.get(key) != null) {
                        sb.append(key).append("=").append(body.get(key));
                        if (i + 1 < len) {
                            sb.append("&");
                        }
                    }
                }
            }

        } catch (Exception ex) {}

        return sb.toString();

    }

    public static String random(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public static String concat(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        sb.append(Objects.toString(s1, ""));
        if (sb.length() > 0 && StringUtil.isNotEmpty(s2)) {
            sb.append(",");
        }
        sb.append(s2);
        return  sb.toString();
    }

    public static Map<String, String> parseParameterMap(Map<String, String[]> request) {
        Map<String, String> result = new HashMap<>();
        for(Map.Entry<String, String[]> entry : request.entrySet()) {
            result.put(entry.getKey(), entry.getValue()[0]);
        }

        return result;
    }

}
