package uk.co.odinconsultants.dojo.data.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class JavaMergeSortTest {

    private JavaMergeSort toTest;

    @Before
    public void setUp() {
        toTest = new JavaMergeSort<Integer>();
    }

    @Test
    public void sortManyElements() {
        Integer[] actual = {3, 2, 1, 4, 7, 6};
        Comparable[] sorted = toTest.sort(actual.clone());
        assertThat(sorted, is(new Integer[] { 1, 2, 3, 4, 6, 7}));
    }

    @Test
    public void sortRandomElements() {
        Random random = new Random();
        for (int i = 0 ; i < 10 ; i++) {
            int n = random.nextInt(101) + 10;
            Integer[] numbers = new Integer[n];
            for (int j = 0 ; j < n ; j++) {
                numbers[j] = random.nextInt();
            }
            sortAndCheck(numbers);
        }
    }

    @Test
    public void sortEmptyList() {
        sortAndCheck(new Integer[] { });
    }

    @Test
    public void sortOneElement() {
        sortAndCheck(new Integer[] {41});
    }

    @Test
    public void sortTwoElements() {
        sortAndCheck(new Integer[] {41, 1});
    }

    @Test
    public void sortThreeElements() {
        sortAndCheck(new Integer[] {3,2,1});
    }

    private void sortAndCheck(Integer[] xs) {
        Comparable[] sorted = toTest.sort(xs.clone());
        ArrayList<Comparable> actual = new ArrayList<Comparable>();
        for (Comparable c : sorted) {
            actual.add(c);
        }
        checkWithJavaSort(actual);
    }

    private void checkWithJavaSort(ArrayList<? extends Comparable> actual) {
        ArrayList<? extends Comparable> sorted = (ArrayList<? extends Comparable>) actual.clone();
        Collections.sort(sorted);
        assertThat(actual, is(equalTo(sorted)));
    }
}
