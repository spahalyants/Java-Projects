import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TextAnalysisApp {

    public static void main(String[] args) {
        // Load the text file content
        String filePath = "path/to/your/textfile.txt"; // Change this to your file path
        String content = readFile(filePath);

        if (content == null) {
            System.out.println("Failed to read the file.");
            return;
        }

        // Input from user for particular symbol and word
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a particular symbol to count: ");
        char symbol = scanner.next().charAt(0);
        scanner.nextLine(); // consume newline
        System.out.print("Enter a particular word to count: ");
        String word = scanner.nextLine();

        // Create and execute tasks
        ExecutorService executor = Executors.newFixedThreadPool(6);

        Future<Integer> vowelCount = executor.submit(() -> countVowels(content));
        Future<Integer> consonantCount = executor.submit(() -> countConsonants(content));
        Future<Integer> numberCount = executor.submit(() -> countNumbers(content));
        Future<Integer> totalSymbolCount = executor.submit(() -> content.length());
        Future<Integer> particularSymbolCount = executor.submit(() -> countParticularSymbol(content, symbol));
        Future<Integer> particularWordCount = executor.submit(() -> countParticularWord(content, word));

        try {
            System.out.println("Number of vowels: " + vowelCount.get());
            System.out.println("Number of consonants: " + consonantCount.get());
            System.out.println("Number of numbers: " + numberCount.get());
            System.out.println("Total number of symbols: " + totalSymbolCount.get());
            System.out.println("Number of occurrences of symbol '" + symbol + "': " + particularSymbolCount.get());
            System.out.println("Number of occurrences of word '" + word + "': " + particularWordCount.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        scanner.close();
    }

    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                content.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

    private static int countVowels(String content) {
        int count = 0;
        for (char c : content.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countConsonants(String content) {
        int count = 0;
        for (char c : content.toCharArray()) {
            if (isConsonant(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countNumbers(String content) {
        int count = 0;
        for (char c : content.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countParticularSymbol(String content, char symbol) {
        int count = 0;
        for (char c : content.toCharArray()) {
            if (c == symbol) {
                count++;
            }
        }
        return count;
    }

    private static int countParticularWord(String content, String word) {
        int count = 0;
        String[] words = content.split("\\s+"); // Split by whitespace
        for (String w : words) {
            if (w.equals(word)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    private static boolean isConsonant(char c) {
        return "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz".indexOf(c) != -1;
    }
}