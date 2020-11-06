package codewars.fundamentals.six;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.runners.JUnit4;

public class KataTest {
    @Test
    public void exampleTests() {
        Assert.assertEquals('j', Kata.findMissingLetter(new char[]{'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n'}));
        Assert.assertEquals('q', Kata.findMissingLetter(new char[]{'n', 'o', 'p', 'r', 's', 't'}));
        Assert.assertEquals('b', Kata.findMissingLetter(new char[]{'a', 'c', 'd', 'e', 'f', 'g', 'h'}));
        Assert.assertEquals('e', Kata.findMissingLetter(new char[]{'a', 'b', 'c', 'd', 'f'}));
        Assert.assertEquals('P', Kata.findMissingLetter(new char[]{'O', 'Q', 'R', 'S'}));
    }
}