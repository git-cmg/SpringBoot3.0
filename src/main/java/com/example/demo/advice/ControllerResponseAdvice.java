package com.example.demo.advice;

import com.example.demo.common.ResponseVo;
import com.example.demo.enums.APIExceptionCode;
import com.example.demo.exception.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一包装响应
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.qianxin.antivirus")
public class ControllerResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
//        response是ResponseVo类型的不进行包装
        return !(returnType.getParameterType().isAssignableFrom(ResponseVo.class)
//                注释了NotControllerResponseAdvice注解的不进行包装
                || returnType.hasMethodAnnotation(NotControllerResponseAdvice.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
//                将数据包装在ResponseVo中转换为json进行返回
                return objectMapper.writeValueAsString(new ResponseVo(body));
            } catch (JsonProcessingException e) {
                throw new APIException(APIExceptionCode.RESPONSE_PACK_ERROR.getErrorCode(), e.getMessage());
            }
        }
        return new ResponseVo(body);
    }
}
