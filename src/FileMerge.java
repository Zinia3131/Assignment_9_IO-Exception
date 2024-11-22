import java.io.*;

public class FileMerge {

    public static void main(String[] args) {
        // Define file paths for input and output files
        String inputFile1 = "input1.txt";  // First input file
        String inputFile2 = "input2.txt";  // Second input file
        String outputFile = "merged_output.txt";  // Output file

        // Merge the two input files into the output file
        try {
            mergeFiles(inputFile1, inputFile2, outputFile);
            System.out.println("Files have been merged successfully into: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error during file merging: " + e.getMessage());
        }
    }

    // Method to merge two files into one output file
    private static void mergeFiles(String inputFile1, String inputFile2, String outputFile) throws IOException {
        // Create BufferedReader to read from input files and BufferedWriter to write to the output file
        try (BufferedReader reader1 = new BufferedReader(new FileReader(inputFile1));
             BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;

            // Read and write content from the first file
            while ((line = reader1.readLine()) != null) {
                writer.write(line);
                writer.newLine();  // Adds a new line after each line from the first file
            }

            // Read and write content from the second file
            while ((line = reader2.readLine()) != null) {
                writer.write(line);
                writer.newLine();  // Adds a new line after each line from the second file
            }
        }
    }
}

