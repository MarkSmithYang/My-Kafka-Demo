package com.yb.kafka.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
  * @Description: 控制层服务异常统一处理类
  * @author yangbiao
  * @date 2018/11/7
 */
@RestControllerAdvice
//@Profile(value = {"dev","test","prop"})
public class GlobalExceptionHander {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public JSONObject runtimeException(RuntimeException e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.BAD_REQUEST.value());
        jsonObject.put("message", "网络错误");
        jsonObject.put("throwMessage", e.getMessage());
        return jsonObject;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public JSONObject Exception(Exception e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.BAD_REQUEST.value());
        jsonObject.put("message", "网络异常");
        jsonObject.put("throwMessage", e.getMessage());
        return jsonObject;
    }
}
