//package com.lt.dailytest.config.jsckon;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
///**
// * @author tong.luo
// * @description JacksonConfig
// * 配置Jackson实体
// * @date 2021/7/22 17:27
// */
////@Configuration
//public class JacksonConfig {
//    /*@Bean
//    @Primary
//    @ConditionalOnMissingBean(ObjectMapper.class)
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        //为objectMapper注册一个带有SerializerModifier的Factory
//        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
//                .withSerializerModifier(new MyBeanSerializerModifier()));
//
//        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
//        serializerProvider.setNullValueSerializer(new CustomizeNullJsonSerializer.NullObjectJsonSerializer());
//        return objectMapper;
//    }*/
//}
