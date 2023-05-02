import java.io.IOException;

public class HTTPClient {
    public static void main(String[] args) throws IOException {
        // Set the URL of the resource to be fetched
        String url = "http://localhost:8070/index.html";

        // Send an HTTP GET request to the specified URL and store the response as a string
        String response = sendGetRequest(url);

        // Write the response string to a file called "index.html"
        writeToFile(response, "index.html");

        // Open the "index.html" file in the default web browser
        openInBrowser("index.html");
    }


    /** 
    * Send an HTTP GET request to a specified URL (server).
    * @param url represents the URL to which the GET request will be sent.
    * @throws IOException if there problem in network connectivity issues, connection timeouts.
    * @return returns the response as a String 
    */
    private static String sendGetRequest(String url) throws IOException{
        //Need to implement this method
        return "response";
    }

    /** 
    * Writes the specified content string to a file with the specified file name.
    * @param content represents the content to be written to the file.
    * @param fileName represents the name of the file to which the content will be written.
    * @throws IOException if there problem in writing the content to the file.
    */
    private static void writeToFile(String content, String fileName) throws IOException {
        //Need to implement this method;
    }

    /** 
    * Opens a file in the default web browser
    * @param fileName represents the name of the file to be opened in the web browser.
    * @throws IOException if there problem in  while opening the file in the web browser.
    * @return boolean return True if the file was successfully opened
    *         returned False if an IOException occurred while attempting to open the file.
    */
    private static boolean openInBrowser(String fileName) throws IOException {
        //Need to implement this method
        return true;
    }
}