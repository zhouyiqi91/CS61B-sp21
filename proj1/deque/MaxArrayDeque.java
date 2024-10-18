package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }

    public T max(Comparator<T> newC) {
        if (isEmpty()) {
            return null;
        }
        T res = get(0);
        for (int i = 1; i < size(); i++) {
            if (newC.compare(get(i), res) > 0) {
                res = get(i);
            }
        }
        return res;
    }

    public T max() {
        return max(c);
    }

}
