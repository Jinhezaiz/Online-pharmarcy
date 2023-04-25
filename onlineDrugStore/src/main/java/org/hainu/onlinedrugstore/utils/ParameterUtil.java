package org.hainu.onlinedrugstore.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * 查询参数识别
 */
public class ParameterUtil {

    private ParameterUtil() {

    }

    public static <T> Map<String, Object> getParametersMap(QueryWrapper<T> queryWrapper, Page<T> page, HttpServletRequest request) {
        Map<String, Object> paramter = Maps.newHashMap();
        Assert.notNull(request, "Request must not be null");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith("like_")) {
                processLike(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("equel_")) {
                processEquel(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("nequel_")) {
                processNequel(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("gt_")) {
                processGt(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("lt_")) {
                processLt(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("gte_")) {
                processGte(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("lte_")) {
                processLte(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("range_")) {
                processRange(paramter, queryWrapper, paramName, request);
            } else if (paramName.startsWith("in_")) {
                String unprefixed = paramName.substring("in_".length());
                String[] values = request.getParameterValues(paramName);
                queryWrapper.in(unprefixed, values);
                paramter.put(paramName, values);
            } else if ("current".equals(paramName)) {
                processCurrent(page, paramName, request);
            } else if ("size".equals(paramName)) {
                processSize(page, paramName, request);
            }
        }
        return paramter;
    }

    private static <T> void processLike(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("like_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            if (values.length > 1) {
                queryWrapper.like(unprefixed, values);
            } else {
                String itemValue = values[0] == null ? null : values[0].trim();
                if (!StringUtils.isEmpty(itemValue)) {
                    queryWrapper.like(unprefixed, itemValue);
                    paramter.put(paramName, itemValue);
                }
            }
        }
    }

    private static <T> void processEquel(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("equel_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            if (values.length > 1) {
                queryWrapper.eq(unprefixed, values);
            } else {
                String itemValue = values[0] == null ? null : values[0].trim();
                if (!StringUtils.isEmpty(itemValue)) {
                    queryWrapper.eq(unprefixed, itemValue);
                    paramter.put(paramName, itemValue);
                }
            }
        }
    }

    private static <T> void processNequel(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("nequel_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            if (values.length > 1) {
                queryWrapper.ne(unprefixed, values);
            } else {
                String itemValue = values[0] == null ? null : values[0].trim();
                if (!StringUtils.isEmpty(itemValue)) {
                    queryWrapper.ne(unprefixed, itemValue);
                    paramter.put(paramName, itemValue);
                }
            }
        }
    }

    private static <T> void processGt(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("gt_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            if (values.length > 1) {
                queryWrapper.gt(unprefixed, values);
            } else {
                String itemValue = values[0] == null ? null : values[0].trim();
                if (!StringUtils.isEmpty(itemValue)) {
                    queryWrapper.gt(unprefixed, itemValue);
                    paramter.put(paramName, itemValue);
                }
            }
        }
    }

    private static <T> void processLt(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("lt_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            if (values.length > 1) {
                queryWrapper.lt(unprefixed, values);
            } else {
                String itemValue = values[0] == null ? null : values[0].trim();
                if (!StringUtils.isEmpty(itemValue)) {
                    queryWrapper.lt(unprefixed, itemValue);
                    paramter.put(paramName, itemValue);
                }
            }
        }
    }

    private static <T> void processGte(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("gte_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            if (values.length > 1) {
                queryWrapper.ge(unprefixed, values);
            } else {
                String itemValue = values[0] == null ? null : values[0].trim();
                if (!StringUtils.isEmpty(itemValue)) {
                    queryWrapper.ge(unprefixed, itemValue);
                    paramter.put(paramName, itemValue);
                }
            }
        }
    }

    private static <T> void processLte(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("lte_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            if (values.length > 1) {
                queryWrapper.le(unprefixed, values);
            } else {
                String itemValue = values[0] == null ? null : values[0].trim();
                if (!StringUtils.isEmpty(itemValue)) {
                    queryWrapper.le(unprefixed, itemValue);
                    paramter.put(paramName, itemValue);
                }
            }
        }
    }

    private static <T> void processRange(Map<String, Object> paramter, QueryWrapper<T> queryWrapper, String paramName, HttpServletRequest request) {
        String unprefixed = paramName.substring("range_".length());
        String[] values = request.getParameterValues(paramName);
        if (values != null && values.length != 0) {
            String itemValue = values[0] == null ? null : values[0].trim();
            if (!StringUtils.isEmpty(itemValue)) {
                String[] rangeTime = itemValue.split("到");
                Date start = DateUtil.parseToDay0(rangeTime[0], "yyyy-MM-dd");
                Date end = DateUtil.parseToDay59(rangeTime[1], "yyyy-MM-dd");
                queryWrapper.gt(unprefixed, start);
                queryWrapper.le(unprefixed, end);
                paramter.put(paramName, itemValue);
            }
        }
    }

    private static <T> void processCurrent(Page<T> page, String paramName, HttpServletRequest request) {
        String[] values = request.getParameterValues(paramName);
        if (page != null && values != null && values.length != 0) {
            page.setCurrent(values[0] == null ? 1 : Long.valueOf(values[0].trim()));
        }
    }

    private static <T> void processSize(Page<T> page, String paramName, HttpServletRequest request) {
        String[] values = request.getParameterValues(paramName);
        if (page != null && values != null && values.length != 0) {
            page.setSize(values[0] == null ? 10 : Long.valueOf(values[0].trim()));
        }
    }

    /**
     * request中参数转map
     *
     * @param request
     * @return Map<Object>
     * @author longbh
     * @date 2023/3/3 上午9:08
     */
    public static Map<String, Object> paramsToMap(HttpServletRequest request) {
        Map<String, Object> paramter = Maps.newHashMap();
        Assert.notNull(request, "Request must not be null");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                paramter.put(paramName, values[0]);
            }
        }
        return paramter;
    }
}
