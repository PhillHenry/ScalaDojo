package uk.co.odinconsultants.dojo.data.search;

public class JavaBinarySearch<T extends Comparable> {

    private final T[] items;

    public static final int INVALID = -1;

    public JavaBinarySearch(T[] items) {
        this.items = items;
    }

    private int find(int min, int max, T toFind) {
        int delta = max - min;
        if (delta < 0) {
            return INVALID;
        } else if (delta <= 1) {
            if (items[min].compareTo(toFind) == 0) {
                return min;
            } else if (items[max].compareTo(toFind) == 0) {
                return max;
            } else {
                return INVALID;
            }
        } else {
            int midpoint = min + (delta / 2);
            T element = items[midpoint];
            int comparison = toFind.compareTo(element);
            if (comparison == 0) {
                return midpoint;
            } else if (comparison > 0) {
                return find(midpoint, max, toFind);
            } else {
                return find(min, midpoint, toFind);
            }
        }
    }

    public int findIndexOf(T t) {
        return find(0, items.length - 1, t);
    }

}
