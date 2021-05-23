package uk.co.odinconsultants.dojo.data.tries;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    @Test
    public void addAndRetrieveSimilarWords() {
        String[] words = {"she", "sells", "sea", "shells"};
        for (String word : words) {
            toTest.addWord(word);
            JavaTries found = toTest.retrieveWord(word);
            assertThat(found, is(notNullValue()));
        }
        for (String word : words) {
            JavaTries found = toTest.retrieveWord(word);
            assertThat(found, is(notNullValue()));
        }
    }

    @Test
    public void unknownWordReturnsNull() {
        assertThat(toTest.retrieveWord("never_added"), is(nullValue()));
    }

    @Test
    public void retrieveSubstringContainsRest() {
        toTest.addWord("shells");
        JavaTries she = toTest.retrieveWord("she");
        assertThat(she.retrieveWord("lls"), is(notNullValue()));
    }
}
