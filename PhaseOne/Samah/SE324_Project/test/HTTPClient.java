import java.io.IOException;

import org.junit.jupiter.api.Test;

public class HTTPClient {
    public static void main(String[] args) throws IOException {
        
    }
/*---------------------------------------Test strategy------------------------------------------- */
    /*Partition:
     * 1. Valid URL input
     * 
     *    Subdomains:
     *    1.0. URL is valid and returns a valid HTML page.
     *    1.1. URL is valid but returns a non-HTML page (e.g. image)
     *      
     *    Test cases:
     *    1. url = "http://localhost:8070/index.html" Cover Subdomain 1.0
     *    2. url = "http://localhost:8070/SSudoku.jpg" Cover Subdomain 1.1
     * 
     * 2. Invalid URL input
     * 
     *    Subdomains:
     *    2.0. URL is invalid (e.g. returns 404 error)
     * 
     *    Test cases:
     *    1. url = "http://localhost:8070/home.html" Cover Subdomain 2.0
     */
    private static String sendGetRequest(String url) throws IOException {
        //Need to implement this method
        return url;
    }

    /*
     * Partition: Valid input
     *
     * Subdomains:
     * 1.0. URL is valid and returns a valid HTML page.
     *
     * Test cases:
     * 1. url = "http://localhost:8070/index.html" Cover Subdomain 1.0
     */
    @Test
    public void testSendGetRequestWithValidUrlAndValidHtmlPage() throws IOException {
        //Need to implement this method
    }

    /*
     * Partition: Valid input
     *
     * Subdomains:
     * 1.1. URL is valid but returns a non-HTML page (e.g. image)
     *
     * Test cases:
     * 2. url = "http://localhost:8070/SSudoku.jpg" Cover Subdomain 1.1
     */
    @Test
    void testSendGetRequestWithValidUrlAndNonHtmlPage() throws IOException {
        //Need to implement this method
    }

    /*
     * Partition: Invalid input
     *
     * Subdomains:
     * 2.0. URL is invalid (e.g. returns 404 error)
     *
     * Test cases:
     *  1. url = "http://localhost:8070/home.html" Cover Subdomain 2.0
     */
    @Test
    void testSendGetRequestWithInvalidUrl() {
        //Need to implement this method
    }
/*-------------------------------------------------------------------------------------------------- */


/*---------------------------------------Testing strategy------------------------------------------- */
    /*Partition:
     * 1. Valid input
     * 
     *    Subdomains:
     *    1.0. Content and file name are not null
     *    1.1. Content contains non-ASCII characters
     *    1.2. File name contains valid characters
     *      
     *    Test cases:
     *    1. File name ="index.html" Cover Subdomains 1.0 & 1.2
     *    2. This file "index.html" not contains any non-ASCII characters Cover Subdomain 1.1
     * 
     * 2. Invalid input
     * 
     *    Subdomains:
     *    2.0. Content is null
     *    2.1. File name is null
     *    2.2. File name contains too many characters > 255
     *    2.3. File name contains invalid characters (e.g. slashes, colons)
     * 
     *    Test cases:
     *    1. content = null cover Subdomain 2.0
     *    2. fileName = null cover Subdomain 2.1
     *    3. fileName = "this_is_a_really_long_file_name_that_is_over_255_characters_and_should_fail.html" cover Subdomain 2.2
     *    4. fileName = "test/invalid.html" cover Subdomain 2.3
     */
    private static void writeToFile(String content, String fileName) throws IOException {
        //Need to implement this method
    }

    /*
     * Partition: Valid input
     *
     * Subdomains:
     * 1.0. Content and file name are not null
     * 1.2. File name contains valid characters
     *
     * Test cases:
     * 1. File name ="index.html" Cover Subdomains 1.0 & 1.2
     */
    @Test
    public void testWriteToFileWithValidInput() throws IOException {
       //Need to implement this method
    }

    /*
     * Partition: Valid input
     *
     * Subdomains:
     * 1.1. Content contains non-ASCII characters
     *
     * Test cases:
     * 2. This file "index.html" not contains any non-ASCII characters Cover Subdomain 1.1
     */
    @Test
    public void testWriteToFileWithValidInputContainingNonASCIISubdomain() throws IOException {
       //Need to implement this method
    }

    /*
     * Partition: Invalid input
     *
     * Subdomains:
     * 2.0. Content is null
     *
     * Test cases:
     * 1. content = null cover Subdomain 2.0
     */
    @Test
    public void testWriteToFileWithNullContentSubdomain() {
        //Need to implement this method
    }

    /*Partition: Invalid input
     * 
     *    Subdomains:
     *    2.1. File name is null
     * 
     *    Test cases:
     *    2. fileName = null cover Subdomain 2.1
     */
    @Test
    public void testWriteToFileWithNullFileNameSubdomain() {
        //Need to implement this method
    }

    /*
     * Partition: Invalid input
     *
     * Subdomains:
     * 2.2. File name contains too many characters > 255
     *
     * Test cases:
     * 3. fileName = "this_is_a_really_long_file_name_that_is_over_255_characters_and_should_fail.html" cover Subdomain 2.2
     */
    @Test
    public void testWriteToFileWithTooLongFileNameSubdomain() {
        //Need to implement this method
    }

    /*
     * Partition: Invalid input
     *
     * Subdomains:
     * 2.3. File name contains invalid characters (e.g. slashes, colons)
     *
     * Test cases:
     * 4. fileName = "test/invalid.html" cover Subdomain 2.3
     */
    @Test
    void testWriteToFileWithFileNameContainingSlashes() {
        //Need to implement this method
    }
/*--------------------------------------------------------------------------------------------------- */

/*---------------------------------------Testing strategy ------------------------------------------- */
    /*Partition:
     * 0. Null file name input
     * 
     * 1. Valid file name input
     *    Subdomains:
     *    1.0. File exists and is a valid HTML page
     *      
     *    Test cases:
     *    1. content = "<html><body><h1>Hello, world!</h1></body></html>" Cover Subdomain 1.0
     *    2. fileName = "index.html" Cover Subdomain 1.1
     * 
     * 2. Invalid file name input
     * 
     *    Subdomains:
     *    2.0. File does not exist
     *    2.1. File exists but is not an HTML page
     *    2.2. File name contains invalid characters (e.g. slashes, colons)
     * 
     *    Test cases:
     *    1. fileName = "non-existent.html Cover Subdomain 2.0
     *    2. content = "This is not HTML content" 
     *       fileName = "text-file.txt" cover Subdomain 2.1
     *    3. fileName = "folder/index.html" cover Subdomain 2.2
     */
    private static void openInBrowser(String fileName) throws IOException{
        //Need to implement this method
    }
    /* 
    * 0. Null file name input
    */
    @Test
    public void testOpenInBrowserNullFileName() {
        //Need to implement this method
    }

    /* 
    1. Valid file name input
     *    Subdomains:
     *    1.0. File exists and is a valid HTML page
    */
    @Test
    public void testOpenInBrowserValidFileNameWithHTMLFileType() throws IOException {
        //Need to implement this method
    }
    /* 
    * 2. Invalid file name input
    *       Subdomains:
    *       2.0. File does not exist
    */
    @Test
    public void testOpenInBrowserFileDoesNotExist() {
        //Need to implement this method
    }

    /* 
    * 2. Invalid file name input
    *       Subdomains:
    *       2.1. File exists but is not an HTML page
    */
    @Test
    public void testOpenInBrowserFileExistsButIsNotHTML() throws IOException {
        //Need to implement this method
    }
    /* 
    * 2. Invalid file name input
    *       Subdomains:
    *       2.2. File name contains invalid characters (e.g. slashes, colons)
    */
    @Test
    public void testOpenInBrowserInvalidFileNameWithInvalidCharacters() {
        //Need to implement this method
    }
    
}
/*--------------------------------------------------------------------------------------------------- */
