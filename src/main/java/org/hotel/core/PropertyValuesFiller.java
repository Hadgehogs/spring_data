package org.hotel.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyValuesFiller {

    public static void FillPropertyValues(Object consumer, Object source, String propertiesIncluded, String propertiesExcluded) {
        List<String> propertiesListIncluded = new ArrayList<>();
        if (propertiesIncluded != null) {
            propertiesListIncluded = Arrays.asList(propertiesIncluded.split("\\s*,\\s*"));
        }
        List<String> propertiesListExcluded = new ArrayList<>();
        if (propertiesExcluded != null) {
            propertiesListExcluded = Arrays.asList(propertiesExcluded.split("\\s*,\\s*"));
        }

        Class<?> consumerClass = consumer.getClass();
        Class<?> sourceClass = source.getClass();
        for (Field consumerField : consumerClass.getDeclaredFields()) {
            String consumerFieldName = consumerField.getName();
            if (propertiesListExcluded.stream().anyMatch(s -> s.equals(consumerFieldName))) {
                continue;
            }
            if (propertiesListIncluded.size() > 0 && !propertiesListIncluded.stream().anyMatch(s -> s.equals(consumerFieldName))) {
                continue;
            }
            try {
                Field sourceField = sourceClass.getDeclaredField(consumerField.getName());
                sourceField.setAccessible(true);
                Object Value = sourceField.get(source);
                consumerField.setAccessible(true);
                consumerField.set(consumer, Value);

            } catch (NoSuchFieldException e) {
                continue;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void FillPropertyValues(Object consumer, Object source, String propertiesIncluded) {
        FillPropertyValues(consumer, source, propertiesIncluded, null);
    }

    public static void FillPropertyValues(Object consumer, Object source) {
        FillPropertyValues(consumer, source, null, null);
    }
}
