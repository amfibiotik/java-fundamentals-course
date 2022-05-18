package com.bobocode;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * By default it compares only accessible fields, but this can be configured via a constructor property. If no field is
 * available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 */
public class RandomFieldComparator<T> implements Comparator<T> {
    Class<T> clazz;
    Field fieldToCompare;
    boolean compareOnlyAccessibleFields;

    public RandomFieldComparator(Class<T> targetType) {
        this(targetType, true);
    }

    /**
     * A constructor that accepts a class and a property indicating which fields can be used for comparison. If property
     * value is true, then only public fields or fields with public getters can be used.
     *
     * @param targetType                  a type of objects that may be compared
     * @param compareOnlyAccessibleFields config property indicating if only publicly accessible fields can be used
     */
    public RandomFieldComparator(Class<T> targetType, boolean compareOnlyAccessibleFields) {
        clazz = targetType;
        Field[] fields;
        if (!compareOnlyAccessibleFields) {
            fields = targetType.getDeclaredFields();
        } else {
            fields = targetType.getFields();
        }
        this.compareOnlyAccessibleFields = compareOnlyAccessibleFields;
        int randomFieldIndex;
        if (fields.length > 0) {
            randomFieldIndex = ThreadLocalRandom.current().nextInt(fields.length);
            fieldToCompare = fields[randomFieldIndex];
        } else {
            throw new IllegalArgumentException("There are no any comparable fields!");
        }
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value (nulls last).
     */
    @Override
    @SneakyThrows
    public int compare(T o1, T o2) {
        fieldToCompare.setAccessible(!compareOnlyAccessibleFields);
        var field01 = (Comparable) fieldToCompare.get(o1);
        var field02 = (Comparable) fieldToCompare.get(o2);
        fieldToCompare.setAccessible(!compareOnlyAccessibleFields);
        if (field01 == null && field02 == null) return 0;
        if (field01 == null) return 1;
        if (field02 == null) return -1;
        return field01.compareTo(field02);
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return String.format("Random field comparator of class '%s' is comparing '%s'", clazz, fieldToCompare);
    }
}