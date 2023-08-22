import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Implements the methods of the IBookMapperFrontend interface to design the
 * user-facing view of the project.
 * 
 * @author Rea Mammen (rmammen@wisc.edu)
 *
 */
public class BookMapperFrontend implements IBookMapperFrontend {

	private Scanner userInputScanner;
	private IBookMapperBackend backend;
	private IISBNValidator validator;

	public BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator) {
		this.userInputScanner = userInputScanner;
		this.backend = backend;
		this.validator = validator;
	}

	@Override
	public void runCommandLoop() {
		int userInput;
		do {
			displayMainMenu();
			userInput = userInputScanner.nextInt();
			switch (userInput) {
			case 1:
				isbnLookup();
				break;
			case 2:
				titleSearch();
				break;
			case 3:
				setAuthorFilter();
				break;
			case 4:
				System.out.println("Goodbye!");
			}

		} while (userInput != 4);
	}

	@Override
	public void displayMainMenu() {
		System.out.println("You are in the Main Menu:");
		System.out.println("\t1. Lookup ISBN");
		System.out.println("\t2. Search by Title Word");
		System.out.println("\t3. Set Author Name Filter");
		System.out.println("\t4. Exit Application");

	}

	@Override
	public void displayBooks(List<IBook> books) {
		int i = 1;
		for (IBook book : books) {
			System.out.println(String.format("%d. \"%s\" by %s, ISBN: %s\n", i, book.getTitle(), book.getAuthors(),
					book.getISBN13()));
			i++;
		}
	}

	@Override
	public void isbnLookup() {
		System.out.println("You are in the Lookup ISBN Menu:");
		System.out.print("\tEnter ISBN to look up:");
		String isbn = userInputScanner.next();
		if (!validator.validate(isbn)) {
			System.out.println("Invalid ISBN. Try again");
			return;
		}

		IBook book = backend.getByISBN(isbn);
		displayBooks(Arrays.asList(new IBook[] { book }));
	}

	@Override
	public void titleSearch() {
		System.out.println("You are in the Search for Title Word Menu:");
		System.out.print("\tEnter a word to search for in book titles (empty for all books):");
		String title = userInputScanner.next();
		List<IBook> books = backend.searchByTitleWord(title);
		System.out.println(String.format("Matches (%s) %d of %d",
				backend.getAuthorFilter() == null ? "any author" : "author filter: " + backend.getAuthorFilter(),
				books.size(), backend.getNumberOfBooks()));
		displayBooks(books);
	}

	/**
	 * Method corresponding to option 3 of main menu.
	 */
	public void setAuthorFilter() {
		System.out.println("You are in the Set Author Filter Menu:");
		System.out.println("\tAuthor name must currently contain:"
				+ (backend.getAuthorFilter() == null ? "none" : backend.getAuthorFilter()));
		System.out.print("\tEnter a new string for author names to contain (empty for any):");
		// clear buffer
		userInputScanner.nextLine();
		String authorString = userInputScanner.nextLine();
		if (!authorString.equals("")) {

			backend.setAuthorFilter(authorString);

		} else
			backend.resetAuthorFilter();
	}

}
