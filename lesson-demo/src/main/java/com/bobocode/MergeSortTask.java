package com.bobocode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MergeSortTask<T extends Comparable<T>> extends RecursiveTask<List<T>> {

    List<T> listToSort;

    public MergeSortTask(List<T> listToSort) {
        this.listToSort = listToSort;
    }

    @Override
    protected List<T> compute() {

        if (listToSort.size() <= 1) {
            return listToSort;
        }

        List<T> list01;
        List<T> list02;
        list01 = new ArrayList<>(listToSort.subList(0, listToSort.size() / 2));
        list02 = new ArrayList<>(listToSort.subList(listToSort.size() / 2, listToSort.size()));

        MergeSortTask<T> task01 = new MergeSortTask<>(list01);
        MergeSortTask<T> task02 = new MergeSortTask<>(list02);

        task01.fork();
        task02.compute();
        task01.join();


        merge(list01, list02, listToSort);
        return listToSort;

    }

    private void merge(List<T> list01, List<T> list02, List<T> listToSort) {
        int index01 = 0;
        int index02 = 0;
        int indexArray = 0;

        while (index01 < list01.size() && index02 < list02.size()) {
            if (list01.get(index01).compareTo(list02.get(index02)) < 0) {
                listToSort.set(indexArray, list01.get(index01++));
            } else {
                listToSort.set(indexArray, list02.get(index02++));
            }
            indexArray++;
        }

        //analog of System.arraycopy
        for (int i = 0; i < list01.size() - index01; i++) {
            listToSort.set(indexArray++, list01.get(index01 + i));
        }
        //analog of System.arraycopy
        for (int i = 0; i < list02.size() - index02; i++) {
            listToSort.set(indexArray++, list02.get(index02 + i));
        }
    }
}
