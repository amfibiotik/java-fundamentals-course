package com.bobocode;

/**
 * A simple implementation of the Hash Table that allows storing a generic key-value pair. The table itself is based
 * on the array of {@link Node} objects.
 * <p>
 * An initial array capacity is 16.
 * <p>
 * Every time a number of elements is equal to the array size that tables gets resized
 * (it gets replaced with a new array that it twice bigger than before). E.g. resize operation will replace array
 * of size 16 with a new array of size 32. PLEASE NOTE that all elements should be reinserted to the new table to make
 * sure that they are still accessible  from the outside by the same key.
 *
 * @param <K> key type parameter
 * @param <V> value type parameter
 */
public class HashTable<K, V> {

    static final int DEFAULT_TABLE_SIZE = 16;
    private int numberOfElements = 0;
    private int currentTableSize = DEFAULT_TABLE_SIZE;

    Node<K,V>[] table = new Node[currentTableSize];

    /**
     * Puts a new element to the table by its key. If there is an existing element by such key then it gets replaced
     * with a new one, and the old value is returned from the method. If there is no such key then it gets added and
     * null value is returned.
     *
     * @param key   element key
     * @param value element value
     * @return old value or null
     */
    public V put(K key, V value) {
        V result;
        int bucketIndex = Math.abs(hash(key)) % currentTableSize;
        if (table[bucketIndex] != null) {
            Node<K,V> currentNode = table[bucketIndex];
            while (currentNode.getNext() != null) {
                if (currentNode.getKey().equals(key)) {
                    V oldValue = currentNode.getValue();
                    currentNode.setValue(value);
                    return oldValue;
                }
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new Node<>(key, value));
            result = value;
        } else {
            table[bucketIndex] = new Node<>(key, value);
            result = null;
        }
        checkLoading();
        return result;
    }

    private void checkLoading() {
        numberOfElements++;
        if (numberOfElements == currentTableSize) {
            resizeTable();
        }
    }

    private void resizeTable() {
        Node<K,V>[] oldTable = table;
        currentTableSize *= 2;
        table = new Node[currentTableSize];
        numberOfElements = 0;
        for (int i = 0; i < oldTable.length; i++) {
            Node<K,V> currentNode = oldTable[i];
            while (currentNode != null) {
                put(currentNode.getKey(), currentNode.getValue());
                currentNode = currentNode.getNext();
            }
        }
    }

    private int hash(Object key) {
        return key.hashCode() % currentTableSize;
    }

    /**
     * Prints a content of the underlying table (array) according to the following format:
     * 0: key1:value1 -> key2:value2
     * 1:
     * 2: key3:value3
     * ...
     */
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> currentNode = table[i];
            if (currentNode != null) {
                System.out.print(i + ": " + currentNode.getKey() + ":" + currentNode.getValue());
                while (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                    String nodeData = currentNode.getKey() + ":" + currentNode.getValue();
                    System.out.print(" -> " + nodeData);
                }
            System.out.println();
            }
        }
    }
}