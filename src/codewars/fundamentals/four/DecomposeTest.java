package codewars.fundamentals.four;

import static org.junit.Assert.*;

import org.junit.Test;


public class DecomposeTest {

    @Test
    public void test1() {
        Decompose d = new Decompose();
        assertEquals("1 2 4 10", d.decompose(11));
        assertEquals("1 2 3 7 9", d.decompose(12));

        assertNull(d.decompose(6));

//        assertNull(d.decompose(36));
//        assertNull(d.decompose(37));
//        assertNull(d.decompose(40));


    }
}
