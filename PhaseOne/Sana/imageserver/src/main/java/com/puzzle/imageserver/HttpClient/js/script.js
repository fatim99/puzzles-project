/**
 * Sets the source of an image element to the given URL and waits for the image to be loaded.
 * 
 * @param url A string representing the URL of the image to display on the web page.
 * @return {Promise<void>} A promise that resolves when the image has been
 *		 loaded and set as the source of the image element.
 * @throws RuntimeException If an error occurs during the fetch request or conversion of the 
 *     binary data.
 */
function setImageSrc(url) {
  const imageElement = document.getElementById("image");
  return fetch(url)
    .then(function (response) {
      return response.blob();
    })
    .then(function (blob) {
      var dataUrl = URL.createObjectURL(blob);
      imageElement.src = dataUrl;
      imageElement.style.width = "350px";
      imageElement.style.height = "350px";
    })
    .catch(function (error) {
      throw new RuntimeException(
        "An error occurred during the fetch request or conversion of the binary data."
      );
    });
  // @ Test
  // partition:
  // 1. url 
  // subdomain:
  // 1.1 URL length is long >= 2,083 characters in HTTP/1.1.
  // 1.2 URL length is short <= 30 characters.
  // 1.3 30 < URL length is meduim < 2,083.
  // 1.4 URL scheme = https.
  // 1.5 URL scheme = http.
  // 1.6 URL contains uppercase and lowercase characters.
  // 1.7 URL is not valid
  //
  // Test Cases :
  // url = "http://example.com/" + "a" * 2080 (covers 1.1 & 1.5).
  // url = "http://example.com/a" (covers 1.2 & 1.5 ).
  // url = "https://localhost:8080/HexSudokuBlankPuzzle" (covers 1.3 & 1.4).
  // url = "http://EXAMPLE.com/A" (covers 1.6).
  // url = "htp://example.com/image.jpg" (covers 1.7).
}

/**
 * Adds click event listeners to the blank and solved puzzle buttons.
 * When clicked, the function sets the image source of the puzzle to either a blank or solved puzzle image.
 * @param this method has no paramters 
 * @return boolean - Returns true if the function executes successfully.
 * @throws RuntimeException If there is no connection with the server.
 */
async function addPuzzleButtonClickListeners() {
  const blankPuzzleButton = document.getElementById("blank-puzzle");
  const solvedPuzzleButton = document.getElementById("solved-puzzle");

  try {
    if (blankPuzzleButton) {
      blankPuzzleButton.onclick = async function () {
        const url =
          document.title == "Hex Sudoku"
            ? "http://localhost:8080/HexSudokuBlankPuzzle"
            : "http://localhost:8080/SudokuBlankPuzzle";

        await setImageSrc(url);
      };
    }

    if (solvedPuzzleButton) {
      solvedPuzzleButton.onclick = async function () {
        const url =
          document.title == "Hex Sudoku"
            ? "http://localhost:8080/HexSudokuSolvePuzzle"
            : "http://localhost:8080/SudokuSolvedPuzzle";

        await setImageSrc(url);
      };
    }
  } catch (error) {
    throw new RuntimeException("There was no connection with the server.");
  }

  return true;

  // @ Test:
  // partition
  //1. calling the method
  // subdomain:
  // 1.1 click on the button many times.
  // 1.2 click on the button one time.
  //
  // Test Cases:
  // click on the button 20 times (covers 1.1).
  // click on the button 1 time (covers 1.2).
}

addPuzzleButtonClickListeners();
