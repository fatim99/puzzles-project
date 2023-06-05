package com.puzzle.imageserver.HTTPServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@SpringBootApplication
public class ImageserverApplication {

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/image/";

    public static void main(String[] args) {
        SpringApplication.run(ImageserverApplication.class, args);
    }

    /**
     * Returns the file object for the given image name.
     * 
     * @param imageName A string representing the name of the image file to
     *                  retrieve.
     * @return The File object for the specified image file.
     * @throws ResponseStatusException  if the image file does not exist.
     * @throws IllegalArgumentException if imageName is null, empty, or does not
     *                                  meet the specified
     *                                  conditions for a valid image name.
     */
    private File getImageFile(String imageName) throws ResponseStatusException, IllegalArgumentException {
        String imagePath = IMAGE_DIRECTORY + imageName + ".png";
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
        }

        return imageFile;

        // @Test
        // partition:
        // 1. imageName
        // subdomain:
        // 1.1 imageName.MIN_Length = 0.
        // 1.2 imageName.MAX_Length = 255 ( including the file extension).
        // 1.3 0 < imageName.Length < 255.
        // 1.4 string contains any special character except (< : " / \ | ? *).
        // 1.5 String contains uppercase and lowercase char.
        // 1.6 name for an image does not exist.
        //
        // Test Cases:
        // imageName = '' throws IllegalArgumentException (covers 1.1).
        // imageName =
        // 'Supercalifragilisticexpialidocious-Supercalifragilisticexpialidocious-
        // Supercalifragilisticexpialidocious-Supercalifragilisticexpialidocious-
        // Supercalifragilisticexpialidocious-Supercalifragilisticexpialidocious-
        // Supercalifragilisticexpialidocious.png' (covers 1.2).
        // imageName = 'SE324-Project.png' (covers 1.3 & 1.4 & 1.5)
        // imageName = IMAGE must return the same result when we have imageName = image
        // (covers 1.5 )
        // imageName = "apple.png" should throw ResponseStatusException (covers 1.6 )
    }

    /**
     * Reads the bytes of an image file.
     * 
     * @param imageFile File object representing the image file to read.
     * @return byte [] A byte array containing the content of the image file.
     * @throws ResponseStatusException if an error occurs while reading the image
     *                                 file.
     */
    private byte[] readImageBytes(File imageFile) throws ResponseStatusException {
        try {
            return Files.readAllBytes(imageFile.toPath());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading image file", e);
        }
        // @ Test
        // partition:
        // 1. imageFile.
        // subdomain:
        // 1.1 imageFile.size is very large >= 10 MB.
        // 1.2 imageFile.size is very small <= 917 KB.
        // 1.3 917 KB < imageFile.size is medium < 10 MB.
        // 1.4 imageFile.differentFormat.
        // 1.5 imageFile.size is very large and there is not enough memory available to
        // store the returned array.
        //
        // Test Cases:
        // imageFile.bmp size = 10 MB (covers 1.1 & 1.4).
        // imageFile.png size = 917 KB (covers 1.2 & 1.4).
        // imageFile.JPEG size = 1 MB (covers 1.3 & 1.4).
        // call this method 100 times to test for memory leaks should throw
        // ResponseStatusException (covers 1.5).
    }

    /**
     * Retrieves the response image with the given imageName and returns it as a
     * byte array
     * 
     * @param imageName A string representing the name of the image file to retrieve
     * @return {ResponseEntity<byte[]>} a ResponseEntity containing the byte array
     *         as the response body and the appropriate HTTP headers for the image
     *         file type.
     * @throws ResponseStatusException  if the image file does not exist or an error
     *                                  occurs while
     *                                  reading its content.
     * @throws IllegalArgumentException if imageName is empty, contains any special
     *                                  characters
     *                                  other than hyphens and periods.
     * @throws RuntimeException         if an unexpected runtime exception occurs
     *                                  while attempting to load
     *                                  the image file.
     */
    @GetMapping(value = "/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName)
            throws ResponseStatusException, IllegalArgumentException, RuntimeException {
        try {
            File imageFile = getImageFile(imageName);
            byte[] imageBytes = readImageBytes(imageFile);
            return ResponseEntity.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get image: " + imageName, e);
        }

        // @Test
        // partition:
        // 1. imageName
        // subdomain:
        // 1.1 imageName.MIN_Length = 0.
        // 1.2 imageName.MAX_Length = 255 ( including the file extension).
        // 1.3 0 < imageName.Length < 255.
        // 1.4 imageName contains any special character except (< : " / \ | ? *).
        // 1.5 imageName contains uppercase and lowercase char.
        // 1.6 imageBytes is huge and there is not enough memory available to store the
        // returned array.
        // 1.7 imageName does not exist.
        //
        // Test Cases:
        // imageName = '' should throw IllegalArgumentException (covers 1.1).
        // imageName =
        // Supercalifragilisticexpialidocious-Supercalifragilisticexpialidocious-
        // Supercalifragilisticexpialidocious-Supercalifragilisticexpialidocious-
        // Supercalifragilisticexpialidocious-Supercalifragilisticexpialidocious-
        // Supercalifragilisticexpialidocious.png (covers 1.2).
        // imageName = SE324-Project covers 0 < imageName.Length < 255 (covers 1.3 & 1.4
        // & 1.5 ).
        // imageName = IMAGE must return the same result when we have imageName = image
        // covers
        // uppercase and lowercase chars (covers 1.5).
        // call readImageBytes() 100 times should throws RuntimeException (covers 1.6).
        // imageName = "apple.png" shold throw ResponseStatusException (covers 1.7).
    }

}
