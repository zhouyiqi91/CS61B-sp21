package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void testDeque(){
        int N = 100;
        StudentArrayDeque<Integer> student= new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String errString = new String();

        for (int i=0;i<N;i++){
            int randomNumber = StdRandom.uniform(2);
            if (randomNumber==0) {
                student.addFirst(i);
                solution.addLast(i);
                errString += ("addFirst(" + i + ")\n");
            } else {
                student.addLast(i);
                solution.addLast(i);
                errString += ("addLast(" + i + ")\n");
            }
        }

        for (int i=0;i<N;i++){
            int randomNumber = StdRandom.uniform(2);
            if (randomNumber==0) {
                Integer item1 = solution.removeLast();
                Integer item2 = student.removeLast();
                errString += ("removeLast()\n");
                assertEquals(errString, item1, item2);
            } else {
                Integer item1 = solution.removeFirst();
                Integer item2 = student.removeFirst();
                errString += ("removeFirst()\n");
                assertEquals(errString, item1, item2);
            }
        }

    }
}
