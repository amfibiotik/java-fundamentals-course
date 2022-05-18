package com.bobocode;

import java.util.Stack;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 9, 1);
        printReversedRecursively(head);
        printReversedUsingStack(head);
    }

    /**
     * Creates a list of linked {@link Node} objects based on the given array of elements and returns a head of the list.
     *
     * @param elements an array of elements that should be added to the list
     * @param <T>      elements type
     * @return head of the list
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        Node<T> head = new Node<>(elements[0]);
        Node<T> currentNode = head;
        for (int i = 1; i < elements.length; i++) {
            currentNode.next = new Node<>(elements[i]);
            currentNode = currentNode.next;
        }
        return head;
    }

    /**
     * Prints a list in a reserved order using a recursion technique. Please note that it should not change the list,
     * just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedRecursively(Node<T> head) {
        if (head == null) return;
        printReversedRecursively(head.next);
        if (head.next != null) System.out.print(" -> ");
        System.out.print(head.element);
    }

    /**
     * Prints a list in a reserved order using a {@link java.util.Stack} instance. Please note that it should not change
     * the list, just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedUsingStack(Node<T> head) {
        System.out.println();
        Stack<Node<T>> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        while (!stack.empty()) {
            Node<T> current = stack.pop();
            if (current.next != null) System.out.print(" -> ");
            System.out.print(current.element);
        }
        System.out.println();
    }
}