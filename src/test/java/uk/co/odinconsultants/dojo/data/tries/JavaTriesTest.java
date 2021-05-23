package uk.co.odinconsultants.dojo.data.tries;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JavaTriesTest {

    private JavaTries toTest;

    @Before
    public void setUp() {
        toTest = new JavaTries();
    }

    @Test
    public void addAndRetrieveWord() {
        String word = "word";
        toTest.addWord(word);
        JavaTries found = toTest.retrieveWord(word);
        assertThat(found, is(notNullValue()));
    }

}
