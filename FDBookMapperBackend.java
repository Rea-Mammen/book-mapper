import java.util.Arrays;
import java.util.List;

/**
 * Placeholder class to test the functionality of the frontend.
 * @author Rea Mammen rmammen@wisc.edu
 *
 */
public class FDBookMapperBackend implements IBookMapperBackend {

	protected String authorFilter;

	// placeholder implementation for Book1
	class Book1 implements IBook{

		@Override
		public String getTitle() {
			return "Title 1";
		}

		@Override
		public String getAuthors() {
			return "Marcus, Kit";
		}

		@Override
		public String getISBN13() {
			return "2637481928763";
		}
	}
		
	//placeholder implementation for Book2
	class Book2 implements IBook{

		@Override
		public String getTitle() {
			return "Title 2";
		}

		@Override
		public String getAuthors() {
			return "Kit, Princess";
		}

		@Override
		public String getISBN13() {
			return "2637123928763";
		}
		
	}
	
	@Override
	public void addBook(IBook book) {
		// unimplemented
		
	}

	@Override
	public int getNumberOfBooks() {
		// hard-code a set number of books
		return 2;
	}

	@Override
	public void setAuthorFilter(String filterBy) {
		//Back end class needs to process and remember the author filter
		//placeholder doesn't need to process it, only remember it so as to test if FD displays the author filter correctly
		this.authorFilter = filterBy;
	}

	@Override
	public String getAuthorFilter() {
		//Back end class needs to process and remember the author filter

		return this.authorFilter;
	}

	@Override
	public void resetAuthorFilter() {
		// set author filter to null
		
		this.authorFilter = null;
	}

	@Override
	public List<IBook> searchByTitleWord(String word) {
		// return a list of the two hard coded books
		//create an array->convert that array to a list->return as hard-coded list for this method
		//converts java primitive arrays into array lists
		return Arrays.asList(new IBook[]{new Book1(), new Book2()});
	}

	@Override
	public IBook getByISBN(String ISBN) {
		// return one of the books
		return new Book1();
	}

}
