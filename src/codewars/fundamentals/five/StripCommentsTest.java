package codewars.fundamentals.five;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StripCommentsTest {

    @Test
    public void stripComments() throws Exception {
        assertEquals(
                "apples, pears\ngrapes\nbananas",
                StripComments.stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"})
        );

        assertEquals(
                "a\nc\nd",
                StripComments.stripComments("a #b\nc\nd $e f g", new String[]{"#", "$"})
        );

        assertEquals(
                " a\nc",
                StripComments.stripComments(" a #b\nc", new String[]{"#", "$"})
        );

        assertEquals(
                "\n\na\nc",
                StripComments.stripComments("\n\na # b\nc", new String[]{"#", "$"})
        );
        assertEquals(
                "N",
                StripComments.stripComments("N a bbb", new String[]{"a"})
        );


        assertEquals(
                "N\nu\ne",
                StripComments.stripComments("N nb\nu\ne \\e f g", new String[]{"n","\\"})
        );

        assertEquals(
                "a",
                StripComments.stripComments("a#b", new String[]{"#", "$"})
        );
        assertEquals(
                "",
                StripComments.stripComments("", new String[]{"#", "$"})
        );

        assertEquals(
                "",
                StripComments.stripComments("#", new String[]{"#", "$"})
        );
    }

}