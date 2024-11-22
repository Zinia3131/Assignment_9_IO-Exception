import java.io.*;
import java.net.*;
import java.util.*;

public class UrlDownloader {

    public static void main(String[] args) {
        // Specify the path of the file containing URLs
        String urlFilePath = "urls.txt";  // File containing the list of URLs

        // Read the URLs from the file and download their content
        try {
            List<String> urls = readUrlsFromFile(urlFilePath);
            downloadAndSaveUrls(urls);
            System.out.println("Download complete!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to read the URLs from the file
    private static List<String> readUrlsFromFile(String filePath) throws IOException {
        List<String> urls = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String url;
            while ((url = reader.readLine()) != null) {
                urls.add(url.trim());  // Add the URL to the list, trimming any extra spaces
            }
        }
        return urls;
    }

    // Method to download content from URLs and save it to separate files
    private static void downloadAndSaveUrls(List<String> urls) {
        for (String urlString : urls) {
            try {
                // Create URL object and open connection
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");  // Set GET request method
                connection.setConnectTimeout(5000);  // Timeout for connection
                connection.setReadTimeout(5000);  // Timeout for reading data

                // Check if the connection is successful
                int statusCode = connection.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    // Read the content from the URL
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String content;
                        StringBuilder contentBuilder = new StringBuilder();
                        while ((content = reader.readLine()) != null) {
                            contentBuilder.append(content).append(System.lineSeparator());
                        }

                        // Save the content to a file
                        saveContentToFile(urlString, contentBuilder.toString());
                    }
                } else {
                    System.err.println("Failed to fetch content from URL: " + urlString + " (HTTP Status: " + statusCode + ")");
                }

            } catch (IOException e) {
                System.err.println("Error downloading content from URL: " + urlString + " - " + e.getMessage());
            }
        }
    }

    // Method to save the content to a file named after the URL
    private static void saveContentToFile(String urlString, String content) {
        // Use the URL as the file name (sanitize it to avoid invalid characters)
        String fileName = urlString.replaceAll("[^a-zA-Z0-9.-]", "_") + ".html";  // Replace invalid characters with underscore

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Content saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving content to file: " + fileName + " - " + e.getMessage());
        }
    }
}

