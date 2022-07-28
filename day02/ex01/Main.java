import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Input two files *.txt");
            System.exit(-1);
        }
        BufferedReader reader = null;
        String line1 = null;
        String line2 = null;

        try {
            reader = new BufferedReader(new FileReader(args[0]), 10000000);
            line1 = reader.readLine();
            reader = new BufferedReader(new FileReader( args[1]), 10000000);
            line2 = reader.readLine();
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        } finally {
            reader.close();
        }

        Set<String> wordListAll = new HashSet<>();
        List<String> wordList1 = null;
        List<String> wordList2 = null;

        try {
            wordList1 = Arrays.asList(line1.split("\\s+"));
            wordList2 = Arrays.asList(line2.split("\\s+"));
        } catch (NullPointerException exception) {
            System.out.println("File is empty..");
            System.exit(-1);
        }
        wordListAll.addAll(wordList1);
        wordListAll.addAll(wordList2);

        List<Integer> frequencyFile1 = new ArrayList<>(wordListAll.size());
        List<Integer> frequencyFile2 = new ArrayList<>(wordListAll.size());
        for(String word : wordListAll) {
            frequencyFile1.add(Collections.frequency(wordList1, word));
            frequencyFile2.add(Collections.frequency(wordList2, word));
        }

        int numerator = 0;
        for (int i = 0; i < wordListAll.size(); i++) {
            numerator += (frequencyFile1.get(i) * frequencyFile2.get(i));
        }

        double denominator;
        int denominator1 = 0;
        int denominator2 = 0;

        for (Integer n : frequencyFile1) {
            denominator1 += n * n;
        }
        for (Integer n : frequencyFile2) {
            denominator2 += n * n;
        }
        denominator = Math.sqrt(denominator1) * Math.sqrt(denominator2);
        System.out.println(numerator/denominator);
    }
}
