package com.parking.engine.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.Map;

/**
 * @author modani
 */
@Component
@Slf4j
public class SQLFilterUtils {

    /**
     * Create search and order
     * @param tableName
     * @param data
     * @param order
     * @return
     */
    public static String createSearchSqlAndOrder(String tableName, Object data, String order) {
        Map<String, Object> filter = getFilter(data);
        return createSearchSqlAndOrder(tableName, filter, order);
    }

    /**
     * Get filter
     * @param data
     * @return
     */
    private static Map<String, Object> getFilter(Object data) {
        Gson gson = new Gson();
        String dataStr = gson.toJson(data);
        return gson.fromJson(dataStr, Map.class);
    }

    /**
     * Set value for query
     * @param query
     * @param data
     * @return
     */
    public static Query setValueForQuery(Query query, Object data) {
        Map<String, Object> filter = getFilter(data);
        return setValueForQuery(query, filter);
    }

    /**
     * Set value for query
     * @param query
     * @param filter
     * @return
     */
    public static Query setValueForQuery(Query query, Map<String, Object> filter) {
        if (null == filter) return query;
        filter.entrySet().stream().forEach(e -> {
            //set value for query
            if (e.getValue() != null) {
                query.setParameter(e.getKey(), e.getValue());
            }
        });
//        filter.forEach((k, v) -> {
//            query.setParameter(k, v);
//        });
        return query;
    }

    /**
     * Create search sql
     * @param tableName
     * @param data
     * @return
     */
    public static String createSearchSqlEqual(String tableName, Object data) {
        Map<String, Object> filter = getFilter(data);
        return createSearchSqlEqual(tableName, filter);
    }

    /**
     * Create search sql equal and order
     * @param tableName
     * @param filter
     * @param order
     * @return
     */
    public static String createSearchSqlEqualAndOrder(String tableName, Map<String, Object> filter, String order) {
        String result = createSearchSqlEqual(tableName, filter);
        if (null == result) return "";
        return result + " order by " + order;
    }

    /**
     * Create search sql like
     * @param tableName
     * @param data
     * @return
     */
    public static String createSearchSqlLike(String tableName, Object data) {
        Map<String, Object> filter = getFilter(data);
        return createSearchSqlLike(tableName, filter);
    }

    /**
     * Create search sql like and order
     * @param tableName
     * @param filter
     * @param order
     * @return
     */
    public static String createSearchSqlLikeAndOrder(String tableName, Map<String, Object> filter, String order) {
        String result = createSearchSqlLike(tableName, filter);
        if (null == result) return "";
        return result + " order by " + order;
    }

    /**
     * Create search sql equal
     * @param tableName
     * @param filter
     * @return
     */
    public static String createSearchSqlEqual(String tableName, Map<String, Object> filter) {
        if (!StringUtils.isNotBlank(tableName) || null == filter) return "";
        StringBuilder result = new StringBuilder();
        result.append("From " + tableName + " ");
        result.append(" where 1 = 1 ");
        filter.entrySet().stream().forEach(e -> {
            if (e.getValue() != null) {
                result.append(" and " + e.getKey() + "= :" + e.getKey() + " ");
            }
        });
        return result.toString();
    }

    /**
     * Create search sql like
     * @param tableName
     * @param filter
     * @return
     */
    public static String createSearchSqlLike(String tableName, Map<String, Object> filter) {
        if (!StringUtils.isNotBlank(tableName) || null == filter) return "";
        StringBuilder result = new StringBuilder();
        result.append("From " + tableName + " ");
        result.append(" where 1 = 1 ");
        filter.entrySet().stream().forEach(e -> {
            if (e.getValue() != null) {
                result.append(" and " + e.getKey() + " like CONCAT('%',:" + e.getKey() + ",'%')");
            }
        });
        return result.toString();
    }

    //end
}
