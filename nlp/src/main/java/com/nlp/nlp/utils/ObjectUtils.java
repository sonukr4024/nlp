package com.nlp.nlp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import static java.util.Arrays.stream;

public class ObjectUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectUtils.class);

    public static final int DEFAULT_VALUE_LIMIT = 20;
    public static final int DAFAULT_VALUE_OFFSET = 1;
    private static final Gson gsonWithFieldNamingPolicy = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    private static final Gson gsonWithField = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toUpperCase()).create();
    private static final Gson gson = new Gson();
    static long timeStamp = new Date().getTime();

    public static boolean isNotNullOrEmpty(Object object) {
        return null != object && !"".equals(object);
    }

    public static boolean isEmpty(Object object) {
        return !"".equals(object);
    }

    public static boolean isAValidString(String value) {
        boolean isValidated = false;
        if (value != null && !"".equals(value)) {
            isValidated = true;
        }
        return isValidated;
    }

    public static boolean isAValidId(Long id) {
        boolean isValidated = false;
        if (id != null && id > 0) {
            isValidated = true;
        }
        return isValidated;
    }

    public static boolean isAValidId(Integer id) {
        boolean isValidated = false;
        if (id != null && id > 0) {
            isValidated = true;
        }
        return isValidated;
    }

    public static boolean isValidBoolean(Boolean value) {
        if (value != null)
            return true;
        else
            return false;
    }

    public static Map<String, Object> capitalizeKeys(Object object) {
        Gson gson = new Gson();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = gson.fromJson(gson.toJson(object), Map.class);
        Map<String, Object> newMap = map.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toUpperCase(), entry -> entry.getValue()));
        return newMap;
    }

    /**
     * Converts given object to JSON String
     *
     * @param obj
     * @return {@link String}
     */

    public static String objectToJSON(Object obj) {
        return null == obj ? "" : gson.toJson(obj);
    }

    /**
     * Converts given object to JSON String
     *
     * @param map
     * @param clazz
     * @return {@link String}
     */
    public static <T> T mapToJSON(Map<String, Object> map, Class<T> clazz) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        JSONObject json = new JSONObject(map);
        String jsonString = json.toString();
        return (T) gson.fromJson(jsonString, clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> jsonToMap(Object object) {
        return gson.fromJson(gson.toJson(object), Map.class);
    }

    /**
     * @param limit
     * @return
     */
    public static Integer getLimit(Integer limit) {
        if (limit == null) {
            limit = DEFAULT_VALUE_LIMIT;
        }
        return limit;
    }

    /**
     * @param offset
     * @return
     */
    public static Integer getOffset(Integer offset) {
        if (offset == null) {
            offset = DAFAULT_VALUE_OFFSET;
        }
        return offset;
    }

    /**
     * Helper method to find if list is null or is empty. If list is null or size
     * is less than 1 it will return true otherwise false.
     *
     * @param list A Object of {@link List}
     * @return Return <b>true</b> if list is null (or empty) else <b>false</b> if
     * list contain elements
     */
    public static boolean isEmpty(List<? extends Object> list) {
        if (list == null || list.isEmpty() || (list.size() == 1 && list.get(0) == null)) {
            return true;
        }

        return false;
    }

    /**
     * Helper method to find if list is not null or not empty. If list is null or
     * size is less than 1 it will return false otherwise true.
     *
     * @param list A Object of {@link List}
     * @return Return <b>true</b> if list contain elements else <b>false</b> if
     * list is null (or empty)
     */
    public static Boolean isNotEmpty(List<? extends Object> list) {
        return !isEmpty(list);
    }

    /**
     * Converts a jsonObject to Object of specified class <br/>
     * Allows {@code first_name} to {@code firstName} parsing only
     *
     * @param json
     * @param clazz
     * @return Parsed Class object
     */
    public static <T> T parseClass(Object json, Class<T> clazz) {
        return (T) gsonWithFieldNamingPolicy.fromJson(objectToJSON(json), clazz);
    }

    public static <T> T parseClassFromUppercase(Object json, Class<T> clazz) {
        return (T) gsonWithField.fromJson(objectToJSON(json), clazz);
    }

    /**
     * Converts a jsonString to Object of specified class <br/>
     * Allows {@code first_name} to {@code firstName} parsing only
     *
     * @param jsonString
     * @param clazz
     * @return Parsed Class object
     */
    public static <T> T parseClass(String jsonString, Class<T> clazz) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        return (T) gson.fromJson(jsonString, clazz);
    }

    /**
     * @param map
     * @param clazz
     */
    public static <T> List<T> parseMaptoList(List<Map<String, Object>> map, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        map.forEach(doc -> {
            T t = ObjectUtils.mapToJSON(doc, clazz);
            if (t != null)
                list.add(t);
        });
        return list;
    }


    public List<Map<String, Object>> parseJsonFiletoList(String path) {
        Gson gson = new Gson();
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new InputStreamReader(getClass().getResourceAsStream(path)));
            List<Map<String, Object>> labelMaps = gson.fromJson(gson.toJson(object), List.class);
            return labelMaps;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> key) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        if (key != null)
            return t -> map.putIfAbsent(key.apply(t), Boolean.TRUE) == null;
        return null;
    }

    public static NumberFormat fetchGlobalNumberFormat(int maxFractionDigits) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(maxFractionDigits);
        numberFormat.setGroupingUsed(false);
        return numberFormat;
    }

    /**
     * Check for null and return default string if null
     *
     * @param attribute     {@link Object}
     * @param defaultString {@link String}
     * @return
     */
    public static String getOrDefaultString(Object attribute, String defaultString) {
        return attribute != null ? attribute.toString() : defaultString;
    }

    /**
     * Compare double values in string
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int compareDouble(String s1, String s2) {
        return Double.compare(
                Double.parseDouble(StringUtils.defaultIfBlank(s1, "0")),
                Double.parseDouble(StringUtils.defaultIfBlank(s2, "0")));
    }

    /**
     * Combine list of string using given separator
     *
     * @param separator {@link String}
     * @param values
     * @return combinedString {@link String}
     */
    public static String combineString(String separator, final String... values) {
        List<String> listOfValues = new LinkedList<>();
        listOfValues.addAll(Arrays.asList(values));
        listOfValues = listOfValues.stream()
                .filter(l -> StringUtils.isNotBlank(l))
                .map(l -> l.trim())
                .collect(Collectors.toList());
        return StringUtils.join(listOfValues, separator);
    }

    public static Long convertToLong(String str, long defaultVal) {
        Long longVal = defaultVal;
        if (StringUtils.isNotBlank(str)) {
            try {
                longVal = Long.parseLong(str);
            } catch (NumberFormatException e) {
                longVal = defaultVal;
            }
        }
        return longVal;
    }

    public static boolean isValidNumber(String value) {
        return Pattern.compile("^[-+]?[0-9]+(?:\\.[0-9]+)?(?:[eE][-+][0-9]+)?$").matcher(value).find();
    }

    public static boolean isOnlyNumeric(String value) {
        return Pattern.compile("^[-+]?[0-9]+(?:[0-9]+)?(?:[eE][-+][0-9]+)?$").matcher(value).find();
    }

    public static double getDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }
        if (Pattern.compile("^[-+]?[0-9]+(?:\\.[0-9]+)?(?:[eE][-+][0-9]+)?$").matcher(value).find()) {
            return Double.valueOf(value);
        }
        return 0;
    }

    public static Map<String, Object> objectMap(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(data, Map.class);
    }

    public static LinkedHashMap<String, Object> objectLinkedHashMap(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(data, LinkedHashMap.class);
    }

    public static Map<String, String> stringMap(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(data, Map.class);
    }

    public static List<Map<String, Object>> listMap(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(data, List.class);
    }


    public static boolean notNull(Object... objects) {
        return !isNull(objects);
    }

    public static boolean isNull(Object... objects) {
        return stream(objects).anyMatch(o -> isNull(o));
    }

    public static <T> void batch(int BATCH, List<T> models, ICommand command) {
        IntStream.range(0, (models.size() + BATCH - 1) / BATCH).mapToObj(i -> models.subList(i * BATCH, Math.min(models.size(), (i + 1) * BATCH))).forEach(batch -> {
            command.execute(batch);
        });
    }

    public static <T> void batch(List<T> models, ICommand command) {
        batch(1000, models, command);
    }

    public static String keyMap(List<String> lstIdentifier, Map<String, String> dataMap) {
        List<String> keys = new ArrayList<>();
        lstIdentifier.forEach(s -> {
            keys.add(s + "-" + dataMap.getOrDefault(s, "") + "_");
        });
        return String.join("", keys);
    }

    public static String keyMapFromObject(List<String> lstIdentifier, Map<String, Object> dataMap) {
        List<String> keys = new ArrayList<>();
        lstIdentifier.forEach(s -> {
            keys.add(s + "-" + dataMap.getOrDefault(s, "") + "_");
        });
        return String.join("", keys);
    }


    public static void printLog(String title) {
        System.out.println(title + "=" + new Date().getTime());
    }

    public static String replaceFolderSpecialChars(String text) {
        String replaced = text.replaceAll("[^A-Za-z0-9!@%^&_~`=. ]", "_");
        if (replaced.endsWith(" ") || replaced.endsWith(".")) {
            replaced = replaced.substring(0, replaced.length() - 1) + "_";
        }
        return replaced;
    }

    public static String replaceFileName(String text) {
        return text.replaceAll("[;\\/:,*?\\\"<>|&%']", "_").replaceAll(" ", "_");
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static String sha256(String input) {
        try {
            return toHexString(getSHA(input));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return "";
    }

    public Map<String, Object> parseJsonFile(String path) {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new InputStreamReader(getClass().getResourceAsStream(path)));
            Map<String, Object> map = jsonToMap(object);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<List<Object>> parseJsonToCsv(Object jsonList) {
        List<List<Object>> dataList = new ArrayList<>();
        Type type = new TypeToken<ArrayList<Map>>() {
        }.getType();
        List<Map> mapList = new Gson().fromJson(new Gson().toJson(jsonList), type);
        if (CollectionUtils.isNotEmpty(mapList)) {
            dataList.add(addHeaders(mapList));
            mapList.forEach(mapData -> {
                dataList.add(Arrays.asList(mapData.values().toArray()));
            });
        }
        return dataList;
    }

    private static List<Object> addHeaders(List<Map> mapList) {
        List<Object> headers = new ArrayList<>();
        mapList.get(0).keySet().forEach(keys -> {
            headers.add(keys);
        });
        return headers;
    }

    public static String capitaliseFirstChar(String value) {
        if (ObjectUtils.isAValidString(value)) {
            StringBuilder builder = new StringBuilder();
            Arrays.stream(value.split(" ")).forEach(s -> {
                builder.append(s.substring(0, 1).toUpperCase() + s.substring(1)).append(" ");
            });
            return builder.toString().trim();
        }
        return "";
    }

    public static void printLogTime(String msg) {
        long millies = new Date().getTime();
//        System.out.println(msg + " -- mills(" + millies + ") second : " + (millies - timeStamp) / 1000);
    }

    public static long getCRCCode(byte[] bytes) {
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }
}
