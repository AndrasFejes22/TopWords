import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopWords {
    public static void main(String[] args) {
        // Write a function that, given a string of text (possibly with punctuation and line-breaks),
        // returns an array of the top-3 most occurring words, in descending order of the number of occurrences.

        top3("In a village of La Mancha, the name of which I have no desire to call to" +
                "mind, there lived not long since one of those gentlemen that keep a lance" +
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for" +
                "coursing. An olla of rather more beef than mutton, a salad on most" +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra" +
                "on Sundays, made away with three-quarters of his income.");
        top3("The answers to upcoming Wordle puzzles are hidden right in plain sight. "  +
                "Here's how to hack Wordle to discover them, as well as numerous ways to run up your Wordle score to impress your friends");

    }
    public static List<String> top3(String s) {

        //System.out.println("s : " + s);

        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            s = s.toLowerCase(); //lowercase
            if (Character.isLetter(s.charAt(i))) {
                cnt++;
            }
        }
        if (cnt == 0) {//if it does not contain a letter
            List<String> top = new ArrayList<String>();

            s = s.replaceAll("'", " ");

            return top;
        }


        String s2 = s.trim().replaceAll("[^\\w' ]", " ");

        String s3 = s2.trim().replaceAll("\\s{2,}", " ");

        String s4 = s3.replaceAll("_", " ");

        String[] arr = s4.split(" ");

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {//core process

            if (map.containsKey(arr[i])) {
                map.replace(arr[i], map.get(arr[i]), map.get(arr[i]) + 1);

            } else {
                map.put(arr[i], 1);
            }
        }

        //sort, and limit :3
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3)
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        System.out.println("Reverse Sorted Map_top3 : " + reverseSortedMap);

        List<String> top = new ArrayList<String>();

        Set<String> keys = reverseSortedMap.keySet();

        top.addAll(keys);

        System.out.println("results: " + keys);

        return top;
    }
}

