import java.io.*;
import java.util.Scanner;

public class WordReplacer {

    public static void main(String[] args) {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Input file path (change as necessary)
        String filePath = "input.txt"; // Replace with your file path

        // Get word to replace and the replacement word from the user
        System.out.print("Enter the word to replace: ");
        String oldWord = scanner.nextLine();

        System.out.print("Enter the new word: ");
        String newWord = scanner.nextLine();

        // Step 1: Read the file, replace the word, and write back the modified content
        try {
            replaceWordInFile(filePath, oldWord, newWord);
            System.out.println("The word has been replaced successfully.");
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }

    // Method to read the file, replace words, and write back the modified content
    private static void replaceWordInFile(String filePath, String oldWord, String newWord) throws IOException {
        // Read the file and store the modified content
        StringBuilder modifiedContent = new StringBuilder();

        // Read the file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Loop through each line of the file
            while ((line = reader.readLine()) != null) {
                // Replace the target word with the new word
                String modifiedLine = line.replace(oldWord, newWord);
                // Append the modified line to the content
                modifiedContent.append(modifiedLine).append(System.lineSeparator());
            }
        }

        // Write the modified content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(modifiedContent.toString());
        }
    }
}

