import java.io.*;
import java.util.regex.*;

public class EmailValidator {

    public static void main(String[] args) {
        // Specify the path of the input file containing email addresses
        String inputFilePath = "emails.txt"; // Input file with email addresses
        String outputFilePath = "valid_emails.txt"; // Output file for valid emails

        try {
            // Read email addresses from the input file
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            String email;
            while ((email = reader.readLine()) != null) {
                email = email.trim(); // Remove any leading or trailing whitespace
                if (isValidEmail(email)) {
                    // Write valid email to output file
                    writer.write(email);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();
            System.out.println("Valid email addresses have been written to " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to validate an email address using regular expression
    private static boolean isValidEmail(String email) {
        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the email against the regular expression
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the regex, otherwise false
        return matcher.matches();
    }
}

