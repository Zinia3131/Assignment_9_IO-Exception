import java.io.*;
import java.util.*;

public class ConfigFileEditor {
    private static final String CONFIG_FILE_PATH = "config.properties"; // Path to the properties file
    private static Properties configProperties = new Properties();

    public static void main(String[] args) {
        // Load the properties file
        loadConfigFile();

        // Show the available options
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nConfiguration File Editor");
            System.out.println("1. View Configuration");
            System.out.println("2. Update Configuration");
            System.out.println("3. Save Changes");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    viewConfiguration();
                    break;
                case 2:
                    updateConfiguration(scanner);
                    break;
                case 3:
                    saveConfigFile();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Load properties from the file
    private static void loadConfigFile() {
        try (FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            configProperties.load(inputStream);
            System.out.println("Configuration file loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading the configuration file: " + e.getMessage());
        }
    }

    // View current properties in the file
    private static void viewConfiguration() {
        if (configProperties.isEmpty()) {
            System.out.println("No configuration available.");
        } else {
            System.out.println("\nCurrent Configuration:");
            configProperties.list(System.out);  // Prints key-value pairs
        }
    }

    // Update a specific property
    private static void updateConfiguration(Scanner scanner) {
        System.out.print("Enter the key you want to update: ");
        String key = scanner.nextLine();

        // Check if the key exists
        if (configProperties.containsKey(key)) {
            System.out.print("Enter the new value for " + key + ": ");
            String newValue = scanner.nextLine();
            configProperties.setProperty(key, newValue);
            System.out.println("Value updated successfully.");
        } else {
            System.out.println("Key not found in configuration.");
        }
    }

    // Save the updated properties back to the file
    private static void saveConfigFile() {
        try (FileOutputStream outputStream = new FileOutputStream(CONFIG_FILE_PATH)) {
            configProperties.store(outputStream, "Updated configuration");
            System.out.println("Changes saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving the configuration file: " + e.getMessage());
        }
    }
}
