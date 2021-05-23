package uk.co.odinconsultants.dojo.data.search;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.odinconsultants.dojo.data.search.JavaBinarySearch.INVALID;

public class JavaBinarySearchTest {

    private Integer[] items;

    private JavaBinarySearch toTest;

    @Before
    public void setUp() {
        items = new Integer[] { 1,3,5,7,11,13,17 };
        toTest = new JavaBinarySearch(items);
    }

    @Test
    public void emptyReturnsInvalid() {
        toTest = new JavaBinarySearch(new Integer[] { });
        assertThat(toTest.findIndexOf(42), is(INVALID));
    }

    @Test
    public void findElementIn1Item() {
        int item = 42;
        toTest = new JavaBinarySearch(new Integer[] {item});
        assertThat(toTest.findIndexOf(item), is(0));
        assertThat(toTest.findIndexOf(item + 1), is(INVALID));
    }

    @Test
    public void findElementIn2Items() {
        int item = 42;
        toTest = new JavaBinarySearch(new Integer[] { 0, item });
        assertThat(toTest.findIndexOf(item), is(1));
        assertThat(toTest.findIndexOf(item + 1), is(INVALID));
    }

    @Test
    public void findElementIn3Items() {
        int item = 42;
        toTest = new JavaBinarySearch(new Integer[] { 0, 1, item });
        assertThat(toTest.findIndexOf(item), is(2));
        assertThat(toTest.findIndexOf(item + 1), is(INVALID));
    }

    @Test
    public void nonExistentNumberReturnsInvalid() {
        int index = toTest.findIndexOf(items[items.length - 1] + 42);
        assertThat(index, is(INVALID));
    }

    @Test
    public void findFirstIsIndex0() {
        assertThat(toTest.findIndexOf(items[0]), is(0));
    }

    @Test
    public void findLastIsIndexNMinus1() {
        int last = items.length - 1;
        assertThat(toTest.findIndexOf(items[last]), is(last));
    }

    @Test
    public void findAll() {
        for (int i = 0 ; i < items.length ; i++) {
            assertThat(toTest.findIndexOf(items[i]), is(i));
        }
    }


}
