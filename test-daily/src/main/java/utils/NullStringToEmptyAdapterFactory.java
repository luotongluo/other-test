package utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * @author tong.luo
 * @description NullStringToEmptyAdapterFactory
 * @date 2021/1/13 11:26
 */
public class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
    /**
     * Returns a type adapter for {@code type}, or null if this factory doesn't
     * support {@code type}.
     *
     * @param gson
     * @param type
     */
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType != String.class) {
            return null;
        }
        return (TypeAdapter<T>) new StringNullAdapter();
    }
}
