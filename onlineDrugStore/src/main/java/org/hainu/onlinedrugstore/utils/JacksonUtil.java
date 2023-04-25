package org.hainu.onlinedrugstore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * json转换集合类型
 *
 * @author linwh
 */
public class JacksonUtil {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final ObjectMapper mapper = new JacksonObjectMapper();

    static {
        mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, false);
        mapper.setDateFormat(new SimpleDateFormat(DATE_PATTERN));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private JacksonUtil() {
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 转换集合类型
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> List<T> convertList(String jsonString, Class<T> clazz) {
        if (jsonString != null && !"".equals(jsonString)) {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            List<T> list = null;
            try {
                list = mapper.readValue(jsonString, javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return list;
        }

        return Lists.newArrayList();
    }

    /**
     * 转换集合类型
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> Set<T> convertSet(String jsonString, Class<T> clazz) {
        if (jsonString != null && !"".equals(jsonString)) {
            JavaType javaType = getCollectionType(HashSet.class, clazz);
            Set<T> list = null;
            try {
                list = mapper.readValue(jsonString, javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return list;
        }
        return Sets.newHashSetWithExpectedSize(0);
    }

    /**
     * 转换map类型
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> convertMap(String jsonString) {
        if (jsonString != null && !"".equals(jsonString)) {
            JavaType javaType = getCollectionType(HashMap.class, String.class, Object.class);
            try {
                return mapper.readValue(jsonString, javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return Maps.newHashMap();
    }

    /**
     * 转换map类型
     *
     * @param jsonString
     * @return
     */
    public static Map<String, String> convertStrMap(String jsonString) {
        if (jsonString != null && !"".equals(jsonString)) {
            JavaType javaType = getCollectionType(HashMap.class, String.class, String.class);
            try {
                return mapper.readValue(jsonString, javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return Maps.newHashMap();
    }

    /**
     * 对象序列化
     *
     * @param obj 需要序列化的对象
     * @return 返回json字符串
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将JSON转换成object
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Map<String, String> readJsonToMap(String jsonString) {
        try {
            JavaType javaType = getCollectionType(HashMap.class, String.class, String.class);
            return mapper.readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Maps.newHashMapWithExpectedSize(0);
    }
}
