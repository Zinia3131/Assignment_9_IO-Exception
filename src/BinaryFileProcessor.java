import java.io.*;
import java.util.Scanner;

public class BinaryFileProcessor {

    public static void main(String[] args) {
        // File paths for input, output and append file
        String inputFilePath = "input.bin";  // Replace with your input binary file path
        String outputFilePath = "output.bin";  // Output binary file path
        String appendFilePath = "append.bin";  // File to append data

        Scanner scanner = new Scanner(System.in);

        // Step 1: Read the binary file and write to another file
        try {
            // Read input binary file and write to output file
            copyBinaryFile(inputFilePath, outputFilePath);
            System.out.println("Contents of the input binary file have been copied to the output file.");

            // Step 2: Allow the user to append data to the end of the output file
            System.out.print("\nDo you want to append data to the output file? (yes/no): ");
            String appendChoice = scanner.nextLine().trim().toLowerCase();

            if (appendChoice.equals("yes")) {
                appendToFile(outputFilePath, appendFilePath);
                System.out.println("Data has been appended to the output file.");
            }

        } catch (IOException e) {
            System.err.println("Error during file processing: " + e.getMessage());
        }
    }

    // Method to copy contents from the input binary file to the output binary file
    private static void copyBinaryFile(String inputFilePath, String outputFilePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            // Read and write in chunks of 1024 bytes
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    // Method to append the contents of the append file to the output binary file
    private static void appendToFile(String outputFilePath, String appendFilePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(appendFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath, true)) {  // 'true' flag for append mode

            byte[] buffer = new byte[1024];
            int bytesRead;

            // Read and append data in chunks of 1024 bytes
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}

