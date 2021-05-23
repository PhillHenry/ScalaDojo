package uk.co.odinconsultants.dojo.data.sort;

import java.util.ArrayList;
import java.util.Arrays;
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
            int midpoint = length / 2;
            Comparable[] left = sortArray(Arrays.copyOfRange(xs, 0, midpoint));
            Comparable[] right = sortArray(Arrays.copyOfRange(xs, midpoint, length));
            Comparable[] toReturn = new Comparable[length];
            int i = 0, j = 0, k = 0;
            while (k < length) {
                if (i == left.length) {
                    toReturn[k] = right[j];
                    j += 1;
                } else if (j == right.length) {
                    toReturn[k] = left[i];
                    i += 1;
                } else if (left[i].compareTo(right[j]) <= 0) {
                    toReturn[k] = left[i];
                    i += i;
                } else {
                    toReturn[k] = right[j];
                    j += 1;
                }
                k += 1;
            }
            return toReturn;
        }
    }

    public Comparable[] sort(Comparable[] xs) {
        E[] sorted = (E[]) sortArray(xs);
        // TODO merge
        return sorted;
    }

}
