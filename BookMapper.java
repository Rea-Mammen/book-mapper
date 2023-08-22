import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper {

    public static void main(String[] args) throws FileNotFoundException {
	IBookLoader bookLoader = null; // new BookLoader();
        // load the books from the data file
        List<IBook> bookList = null; // bookLoader.loadBooks("books.csv");
        // instantiate the backend
        IBookMapperBackend backend = null; // new BookMapperBackend();
        // add all the books to the backend
        // for (IBook book : bookList) backend.addBook(book);
        // instantiate the isbn validator
        IISBNValidator isbnValidator = null; // new ISBNValidator();
        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        IBookMapperFrontend frontend = null; // new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        // start the input loop of the front end
        // frontend.runCommandLoop();
    }
    
}
