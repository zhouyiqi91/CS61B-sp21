package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

class LongerStringComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        // Compare based on length
        return s1.length() - s2.length();
    }
}

public class MaxArrayDequeTest {
    @Test
    public void maxItemTest() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(String::compareTo);
        mad.addFirst("Apple");
        mad.addFirst("Banana1");
        mad.addFirst("Cherry");
        mad.addFirst("Date");

        assertEquals("Date", mad.max());
        assertEquals("Banana1", mad.max(new LongerStringComparator()));
    }
}
