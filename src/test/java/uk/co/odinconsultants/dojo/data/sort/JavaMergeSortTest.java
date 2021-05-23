package uk.co.odinconsultants.dojo.data.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaMergeSortTest {

    private JavaMergeSort toTest;

    @Before
    public void setUp() {
        toTest = new JavaMergeSort<Integer>();
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
