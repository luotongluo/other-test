//package com.lt.dailytest.config.jsckon;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//
//import java.io.IOException;
//
///**
// * @author tong.luo
// * @description CustomizeNullJsonSerializer
// * 自定义null值序列化处理器
// * @date 2021/7/22 17:23
// */
//public class CustomizeNullJsonSerializer {
//    /**
//     * 处理数组集合类型的null值
//     */
//    public static class NullArrayJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object value, JsonGenerator jsonGenerator,
//                              SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeStartArray();
//            jsonGenerator.writeEndArray();
//        }
//    }
//
//    /**
//     * 处理字符串类型的null值
//     */
//    public static class NullStringJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object value, JsonGenerator jsonGenerator,
//                              SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeString("");
//        }
//    }
//
//    /**
//     * 处理数值类型的null值
//     */
//    public static class NullNumberJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object value, JsonGenerator jsonGenerator,
//                              SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeNumber(0);
//        }
//    }
//
//    /**
//     * 处理boolean类型的null值
//     */
//    public static class NullBooleanJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object value, JsonGenerator jsonGenerator,
//                              SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeBoolean(false);
//        }
//    }
//
//    /**
//     * 处理实体对象类型的null值
//     */
//    public static class NullObjectJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object value, JsonGenerator jsonGenerator,
//                              SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeEndObject();
//        }
//    }
//
//}
