package org.hainu.onlinedrugstore.base.controller;

import org.hainu.onlinedrugstore.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * controller基础类
 *
 * @date 2023/2/20
 */
public class BaseController {

    private static final String MESSAGE = "message";
    @Autowired
    protected HttpServletRequest request;

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String exception(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put(MESSAGE, e.getMessage());
        e.printStackTrace();
        return JacksonUtil.toJson(map);
    }

    protected String sucess() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "1");
        result.put(MESSAGE, "成功");
        return JacksonUtil.toJson(result);
    }

    protected String sucess(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "1");
        result.put("data", data);
        result.put(MESSAGE, "成功");
        return JacksonUtil.toJson(result);
    }

    protected String fail() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "0");
        result.put(MESSAGE, "失败");
        return JacksonUtil.toJson(result);
    }

    protected String fail(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "0");
        result.put(MESSAGE, message);
        return JacksonUtil.toJson(result);
    }

    protected String fail(int status) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", status);
        result.put(MESSAGE, "失败");
        return JacksonUtil.toJson(result);
    }

    protected String fail(int status, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", status);
        result.put(MESSAGE, message);
        return JacksonUtil.toJson(result);
    }

}
