
public class Book extends Content {
	/*
	 * Subclass of Content representing a book to be stored in the program
	 */

	private int currentPage;
	private int bookLengthInPages;

	public Book() {
		super();
		currentPage = 0;
		bookLengthInPages = 0;
	}

	public Book(String t, String a, int len, String stat, String[] tagsArr) {
		super(t, a, stat, tagsArr);
		bookLengthInPages = len;
		currentPage = 0;
	}

	public Book(String t, String a, int len, String stat, String[] tagsArr, int curPage) {
		super(t, a, stat, tagsArr);
		bookLengthInPages = len;
		currentPage = curPage;
	}

	// set the current page for this book
	public void setCurrentPage(int newPageNum) {
		currentPage = newPageNum;
	}

	// get the current page for this book
	public int getCurrentPage() {
		return currentPage;
	}

	// get the length of this book in pages
	public int getLength() {
		return bookLengthInPages;
	}

	// set the length of this book in pages
	public void setLength(int len) {
		bookLengthInPages = len;
	}

	// get the class of this item
	public String getClassOfObj() {
		return "Book";
	}

}
