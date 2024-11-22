import java.io.*;

public class LineSeparator {
    public static void main(String[] args) {
        // Input and output file paths
        String inputFilePath = "input.txt"; // Replace with your input file path
        String oddFilePath = "odd_lines.txt"; // Output file for odd lines
        String evenFilePath = "even_lines.txt"; // Output file for even lines

        // BufferedReader for reading the file and BufferedWriters for output files
        BufferedReader reader = null;
        BufferedWriter oddWriter = null;
        BufferedWriter evenWriter = null;

        try {
            // Initialize the BufferedReader and Writers
            reader = new BufferedReader(new FileReader(inputFilePath));
            oddWriter = new BufferedWriter(new FileWriter(oddFilePath));
            evenWriter = new BufferedWriter(new FileWriter(evenFilePath));

            String line;
            int lineNumber = 1;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Check if the line number is odd or even
                if (lineNumber % 2 == 0) {
                    // Write even lines to the even file
                    evenWriter.write(line);
                    evenWriter.newLine();
                } else {
                    // Write odd lines to the odd file
                    oddWriter.write(line);
                    oddWriter.newLine();
                }
                lineNumber++;
            }

            System.out.println("File processing complete.");
        } catch (IOException e) {
            // Handle IOExceptions
            System.err.println("An error occurred while processing the file: " + e.getMessage());
        } finally {
            // Close all resources in the finally block to ensure they are always closed
            try {
                if (reader != null) {
                    reader.close();
                }
                if (oddWriter != null) {
                    oddWriter.close();
                }
                if (evenWriter != null) {
                    evenWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the resources: " + e.getMessage());
            }
        }
    }
}

