package uk.co.odinconsultants.dojo.data.tries;

import java.util.HashMap;
import java.util.Map;

public class JavaTries {

    private final Map<Character, JavaTries> children = new HashMap<>();

    public void addWord(String word) {
        char[] chars = word.toCharArray();
        JavaTries current = this;
        for (Character c : chars) {
            JavaTries child = current.children.get(c);
            if (child == null) {
                child = new JavaTries();
                current.children.put(c, child);
            }
            current = child;
        }
    }

    public JavaTries retrieveWord(String word) {
        char[] chars = word.toCharArray();
        JavaTries current = this;
        for (Character c : chars) {
            JavaTries child = current.children.get(c);
            if (child == null) {
                return null;
            }
            current = child;
        }
        return current;
    }

}
