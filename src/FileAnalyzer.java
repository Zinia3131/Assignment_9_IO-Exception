import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalyzer {

    public static void main(String[] args) {
        // The path to the text file
        String filePath = "sample.txt"; // Change this to the path of your text file

        try {
            // Call method to count words, lines, and characters
            FileStats stats = countFileStats(filePath);

            // Display the results
            System.out.println("Number of lines: " + stats.getLines());
            System.out.println("Number of words: " + stats.getWords());
            System.out.println("Number of characters: " + stats.getCharacters());

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to count lines, words, and characters in the file
    public static FileStats countFileStats(String filePath) throws IOException {
        int lines = 0;
        int words = 0;
        int characters = 0;

        // BufferedReader for reading the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++; // Increment lines
                characters += line.length(); // Count characters in the line

                // Split the line into words and count the number of words
                String[] wordArray = line.split("\\s+");
                words += wordArray.length;
            }
        }

        return new FileStats(lines, words, characters);
    }
}