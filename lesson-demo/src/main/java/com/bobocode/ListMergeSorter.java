package com.bobocode;

import java.util.ArrayList;
import java.util.List;

public class ListMergeSorter {

    public static void main(String[] args) {
        List<Integer> intList = List.of(9, 2, 7, 6, 8, 1);
        System.out.println(mergeSort(intList));
    }


    public static <T extends Comparable<T>> List<T> mergeSort(List<T> listToSort) {

        if (listToSort.size() < 2) return listToSort;

        List<T> list01 = new ArrayList<>(listToSort.subList(0, listToSort.size() / 2));
        List<T> list02 = new ArrayList<>(listToSort.subList(listToSort.size() / 2, listToSort.size()));

        list01 = new ArrayList<>(mergeSort(list01));
        list02 = new ArrayList<>(mergeSort(list02));

        List<T> generalList = new ArrayList<>(listToSort); //to have it mutable

        int index01 = 0;
        int index02 = 0;
        for (int i = 0; i < list01.size() + list02.size(); i++) {
            if (index01 == list01.size()) {
                generalList.set(i, list02.get(index02));
                index02++;
            } else if (index02 == list02.size()) {
                generalList.set(i, list01.get(index01));
                index01++;
            } else if (list01.get(index01).compareTo(list02.get(index02)) < 0) {
                generalList.set(i, list01.get(index01));
                index01++;
            } else {
                generalList.set(i, list02.get(index02));
                index02++;
            }
        }
        return generalList;
    }
}


