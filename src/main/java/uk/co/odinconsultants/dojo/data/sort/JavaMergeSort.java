package uk.co.odinconsultants.dojo.data.sort;

import java.util.ArrayList;
import java.util.List;

public class JavaMergeSort<E extends Comparable> {

    private Comparable[] sortArray(Comparable[] xs) {
        int length = xs.length;
        if (length == 0 || length == 1) {
            return xs;
        } else if (length == 2) {
            Comparable a = xs[0];
            Comparable b = xs[1];
            Comparable[] sorted = new Comparable[2];
            if (a.compareTo(b) <= 0) {
                sorted[0] = a;
                sorted[1] = b;
            } else {
                sorted[0] = b;
                sorted[1] = a;
            }
            return sorted;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public Comparable[] sort(Comparable[] xs) {
        E[] sorted = (E[]) sortArray(xs);
        // TODO merge
        return sorted;
    }

}
