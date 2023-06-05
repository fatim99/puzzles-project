package com.puzzle.imageserver.HttpClient;

import java.awt.*;
import java.io.*;

public class HTTPClient {

    public static void main(String[] args) {
        String filePath = "src/main/java/com/puzzle/imageserver/HttpClient/html/index.html";
        try {
            openHtmlFile(filePath);
        } catch (IOException e) {
            System.err.println("Error opening HTML file: " + e.getMessage());
        }
    }

    /**
     * Opens an HTML file in the default browser.
     * 
     * @param filePath A String representing the path to the HTML file to open.
     * @return there is no return value for this method.
     * @throws IOException if an I/O error occurs while opening the file.
     */
    public static void openHtmlFile(String filePath) throws IOException {
        File htmlFile = new File(filePath);
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(htmlFile.toURI());

        // @ Test :
        // partition:
        // 1. filePath
        // subdomain :
        // 1.1 path length is long >= 260 characters on a Windows system.
        // 1.2 20 < path length is medium < 260 on a Windows system.
        // 1.3 path length is short <= 20 characters on a Windows system.
        // 1.4 path contains directory traversal characters like, "../".
        // 1.5 path contains special characters, such as spaces or non-ASCII characters.
        // 1.6 path for a file does not exist.

        //
        // Test Cases :
        // filePath = "C:\" + "a\" * 126 + "a.txt" (covers 1.1 ).
        // filePath = "../../HttpClient/html/index-1.html" (covers 1.2 & 1.4 & 1.5).
        // filePath = " " covers path length is short (covers 1.3).
        // filePath = "../HTML/index-1.html". (covers 1.6 ).

    }
}
