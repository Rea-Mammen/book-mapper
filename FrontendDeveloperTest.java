
import java.util.Scanner;

/**
 * Test entry point class that simulates user I/O.
 * 
 * @author Rea Mammen rmammen@wisc.edu
 *
 */
public class FrontendDeveloperTest {
	public static void main(String[] args) {
		// runInteractiveSession();
		runTests();
	}

	/**
	 * Instantiates the front end and starts the input loop of the front end.
	 */
	public static void runInteractiveSession() {

		// instantiate the backend
		// placeholder for BookMapperBackend
		IBookMapperBackend backend = new FDBookMapperBackend();

		// instantiate the isbn validator
		// placeholder for ISBNValidator
		IISBNValidator isbnValidator = new IISBNValidator() {
			public boolean validate(String isbn13) {
				return true;
			}
		};
		// instantiate the scanner for user input
		Scanner userInputScanner = new Scanner(System.in);
		// instantiate the front end and pass references to the scanner, backend, and
		// isbn validator to it
		IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);
		// start the input loop of the front end
		frontend.runCommandLoop();
	}

	public static void runTests() {
		System.out.println("Result of test1 : " + test1());
		System.out.println("Result of test2 : " + test2());
		System.out.println("Result of test3 : " + test3());
		System.out.println("Result of test4 : " + test4());
		System.out.println("Result of test5 : " + test5());

	}

	/**
	 * Tests the lookup of a book by it's ISBN.
	 * 
	 * @return true if test passed or false otherwise.
	 */
	public static boolean test1() {
		// 4 to exit loop
		// \n to simulate the user hitting enter after an input
		TextUITester tester = new TextUITester("1\n2637123928763\n4\n");
		runInteractiveSession();
		String output = tester.checkOutput();
		if (output.contains("1. \"Title 1\" by Marcus, Kit, ISBN: 2637481928763"))
			return true;
		else
			return false;
	}

	/**
	 * Tests the lookup of a book by it's title.
	 * 
	 * @return true if test passed or false otherwise.
	 */
	public static boolean test2() {
		TextUITester tester = new TextUITester("2\nTitle\n4\n");
		runInteractiveSession();
		String output = tester.checkOutput();
		if (output.contains("1. \"Title 1\" by Marcus, Kit, ISBN: 2637481928763")
				&& output.contains("2. \"Title 2\" by Kit, Princess, ISBN: 2637123928763"))
			return true;
		else
			return false;
	}

	/**
	 * Test to set the author filter and then search by title.
	 * 
	 * @return true if test passed or false otherwise.
	 */
	public static boolean test3() {

		TextUITester tester = new TextUITester(
				new Input().option("3").parameter("Kit").option("2").parameter("Title").option("4").build());
		runInteractiveSession();
		String output = tester.checkOutput();
		if (output.contains("1. \"Title 1\" by Marcus, Kit, ISBN: 2637481928763")
				&& output.contains("2. \"Title 2\" by Kit, Princess, ISBN: 2637123928763"))
			return true;
		else
			return false;
	}

	/**
	 * Test for exiting the application.
	 * 
	 * @return true if test passed or false otherwise.
	 */
	public static boolean test4() {

		TextUITester tester = new TextUITester(new Input().option("4").build());
		runInteractiveSession();
		String output = tester.checkOutput();
		if (output.contains("Goodbye!"))
			return true;
		else
			return false;
	}

	/**
	 * Test for an option that is not in the main menu.
	 * 
	 * @return true if test passed or false otherwise.
	 */
	public static boolean test5() {
		TextUITester tester = new TextUITester(new Input().option("5").option("4").build());
		runInteractiveSession();
		String output = tester.checkOutput();
		// check for 2 occurrences of the main menu because 1 main menu will always be
		// printed as the initial prompt

		System.out.println(output.replace("You are in the Main Menu:", ""));
		return output.replaceFirst("You are in the Main Menu:", "").contains("You are in the Main Menu:");

	}

	/**
	 * Helper class to efficiently simulate inputs.
	 * 
	 * @author Rea
	 *
	 */
	private static class Input {
		// uses builder pattern to piece together a string
		private StringBuilder builder;

		// creates a new StringBuilder object
		Input() {
			builder = new StringBuilder();
		}

		Input option(String option) {
			builder.append(option);
			builder.append("\n");
			// returns itself (the Input object) to enable chaining
			return this;
		}

		Input parameter(String parameter) {
			builder.append(parameter);
			builder.append("\n");
			return this;
		}

		// converting StringBuilder object to the final string
		String build() {
			return builder.toString();
		}
	}
}