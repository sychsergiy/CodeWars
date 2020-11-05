package codewars.fundamentals.six;

import java.util.ArrayList;
import java.util.List;

// https://www.codewars.com/kata/583203e6eb35d7980400002a/

public class SmileFaces {

    public static int countSmileys(List<String> arr) {
        // declare available eyes
        List<String> eyes = new ArrayList<String>();
        eyes.add(":");
        eyes.add(";");
        // declare available noses
        List<String> noses = new ArrayList<String>();
        noses.add("-");
        noses.add("~");
        // declare available mouses
        List<String> mouthes = new ArrayList<String>();
        mouthes.add(")");
        mouthes.add("D");

        // find all possible smiles by multiplexing all combinations
        List<String> eyesAndNoses = findCombinations(eyes, noses);
        List<String> eyesAndNosesAndMouthes = findCombinations(eyesAndNoses, mouthes);
        List<String> eyesAndMouthes = findCombinations(eyes, mouthes);


        // check each arr item on smile
        int smilesCounter = 0;
        for (String item : arr) {
            if (eyesAndNosesAndMouthes.contains(item) || eyesAndMouthes.contains(item)) {
                smilesCounter++;
            }
        }
        return smilesCounter;
    }

    static public List<String> findCombinations(List<String> a, List<String> b) {
        List<String> allCombinations = new ArrayList<>();
        for (String itemA : a) {
            for (String itemB : b) {
                allCombinations.add(itemA.concat(itemB));
            }
        }
        return allCombinations;
    }
}