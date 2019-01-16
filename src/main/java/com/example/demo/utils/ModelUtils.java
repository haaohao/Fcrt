package com.example.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelUtils {

    private static Logger logger = LoggerFactory.getLogger(ModelUtils.class);

    private ModelUtils() {
    }

    public static Type[] getGenericClasses(Class<?> c) {
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            return ((ParameterizedType) c.getGenericSuperclass())
                    .getActualTypeArguments();
        } else {
            return new Type[0];
        }
    }

    public static Class getUniquenessGenericClass(Class c) {
        Type[] types = getGenericClasses(c);
        if (types != null && types.length > 0) {
            return (Class) types[0];
        } else {
            return null;
        }
    }

    public static String getPrimaryKeyName(Class<?> modelClass) {
        Field field = getPrimaryKeyField(modelClass);
        if (field != null) {
            return field.getName();
        } else {
            return "";
        }
    }

    public static Field getPrimaryKeyField(Class<?> modelClass) {
        List<Field> fields = getAllFields(modelClass);
        for (Field f : fields) {
            if (f.isAnnotationPresent(Id.class)) {
                return f;
            }
        }
        return null;
    }

    public static List<Field> getAllFields(Class<?> modelClass) {
        List<Field> fields = new ArrayList<>();
        if (modelClass != null) {
            fields.addAll(Arrays.asList(modelClass.getDeclaredFields()));
            fields.addAll(getAllFields(modelClass.getSuperclass()));
        }
        return fields;
    }

    public static String getPrimaryKeyValue(Object modelObject) {
        String fieldName = getPrimaryKeyName(modelObject.getClass());
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        try {
            return (String) modelObject.getClass().getMethod(methodName).invoke(modelObject);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;

    }


}
