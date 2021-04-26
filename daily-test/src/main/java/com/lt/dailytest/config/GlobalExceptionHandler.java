package com.lt.dailytest.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author tong.luo
 * @description GlobalExceptionHandler
 * @date 2021/4/26 19:04
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数合法性校验异常
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validationBodyException(MethodArgumentNotValidException exception) {

        StringBuffer buffer = new StringBuffer();

        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();

            errors.forEach(p -> {

                FieldError fieldError = (FieldError) p;
                LOGGER.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
                        "},errorMessage{" + fieldError.getDefaultMessage() + "}");
                buffer.append(fieldError.getDefaultMessage()).append(",");
            });
        }
//        BaseResponse response = new BaseResponse(BusinessCodeEnum.INVALID_PARAM);
//        response.setRespMsg(buffer.toString().substring(0, buffer.toString().length()-1));
//        return JSONObject.toJSONString(response);
        return buffer.toString().substring(0, buffer.toString().length()-1);
    }

}
